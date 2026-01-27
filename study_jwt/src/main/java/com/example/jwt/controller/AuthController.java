package com.example.jwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.config.jwt.JwtProperties;
import com.example.jwt.config.jwt.JwtTokenService;
import com.example.jwt.config.jwt.RefreshTokenCookieFactory;
import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtProperties jwtProperties;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenCookieFactory refreshTokenCookieFactory;
    private final UserRepository userRepository;

    /*
        Access Token 재발급 + Refresh Token 회전(Rotation)
        ※Refresh Token 회전 : https://naver.me/Fm3aNdNh 참고
    */
    @PostMapping("refresh")
    public ResponseEntity<Map<String, Object>> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken) {
        if(refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Refresh token is missing"));
        }

        DecodedJWT decoded;
        try {
            decoded = jwtTokenService.verify(refreshToken);
        }catch(Exception e) { // Refresh Token 검증 실패(서명 불일치, 토큰 만료 등)
            return unauthorizedWithClearCookie("Invalid or expired refresh token");
        }

        String username = decoded.getSubject();
        User user = userRepository.findByUsername(username);

        /*
            ★ 서버가 신뢰하는(현재 유효한) Refresh Token과 일치하지 않음(탈취 의심) ★
            ※https://naver.me/5v3UBcR5 참고
        */
        if(!refreshToken.equals(user.getRefreshToken())) {
            // 추가 피해를 방지하기 위해 Refresh Token을 즉시 무효화
            user.setRefreshToken(null);
            user.setRefreshTokenExpiresAt(null);
            userRepository.save(user);

            return unauthorizedWithClearCookie("Refresh token mismatch");
        }

        // ======================================================= 정상 =======================================================

        // Access Token & Refresh Token 새로 생성
        String newAccessToken = jwtTokenService.createAccessToken(username);
        JwtTokenService.RefreshTokenBundle newRefresh = jwtTokenService.createRefreshToken(username);

        // 새로운 Refresh Token으로 교체
        user.setRefreshToken(newRefresh.token());
        user.setRefreshTokenExpiresAt(newRefresh.expiresAtMillis());
        userRepository.save(user);

        // 새로운 Access Token & Refresh Token 응답
        HttpHeaders headers = new HttpHeaders();
        headers.add(jwtProperties.getHeaderString(), jwtProperties.getTokenPrefix() + newAccessToken);

        ResponseCookie refreshCookie = refreshTokenCookieFactory.create(newRefresh.token(), newRefresh.expiresAtMillis());
        headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return ResponseEntity.ok().headers(headers).body(Map.of("message", "Token refreshed successfully"));
    }

    // 로그아웃
    @PostMapping("logout")
    public ResponseEntity<Map<String, Object>> logout(Authentication authentication) {
        if(authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Unauthenticated"));
        }

        // Refresh Token 무효화
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if(user != null) {
            user.setRefreshToken(null);
            user.setRefreshTokenExpiresAt(null);
            userRepository.save(user);
        }

        // Refresh Token 쿠키를 즉시 만료(제거)시키도록 브라우저에게 지시
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookieFactory.clear().toString());

        return ResponseEntity.ok().headers(headers).body(Map.of("message", "Logged out"));
    }

    // Refresh Token이 유효하지 않을 때의 공통 처리(Refresh Token 쿠키 제거 + 401 UNAUTHORIZED 응답) 메서드
    private ResponseEntity<Map<String, Object>> unauthorizedWithClearCookie(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, refreshTokenCookieFactory.clear().toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .headers(headers)
                .body(Map.of("message", message));
    }
}