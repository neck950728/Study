package com.example.jwt.config.jwt;

import com.example.jwt.config.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenCookieFactory refreshTokenCookieFactory;
    private final UserRepository userRepository;

    // 1. 사용자가 로그인 요청 시  →  이 예제의 경우 /loginProc 요청 시(default : /login)
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            /*
                요청 Body가 JSON 형식이므로, request.getParameter로는 받을 수 없다.
                ※request.getParameter는 form data(key=value 형식)만 받을 수 있다.
            */
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(request.getInputStream(), User.class);


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            /*
                AuthenticationManager.authenticate 메서드를 호출하면, 바로 이때 Spring Security는 내부적으로
                UserDetailsService.loadUserByUsername 메서드를 통해 사용자의 정보를 가져와 인증을 진행한다.
                그리고 인증에 성공하면, 해당 사용자의 정보를 Authentication 객체에 담아 반환해 준다.
            */
            Authentication authentication = authenticationManager.authenticate(authenticationToken);


            /*
                return 시 해당 Authentication 객체가 SecurityContext에 저장된다.
                ※여기서 혼동할 수 있는 점 : https://naver.me/GVELpnzG 참고
            */
            return authentication;
        }catch(IOException e) {
            throw new AuthenticationServiceException("Failed to parse authentication request body", e);
        }
    }

    // 2. 인증 성공 시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
        String username = principalDetails.getUsername();

        // Access Token & Refresh Token 생성
        String accessToken = jwtTokenService.createAccessToken(username);
        JwtTokenService.RefreshTokenBundle refreshToken = jwtTokenService.createRefreshToken(username);

        User user = userRepository.findByUsername(username);
        // Refresh Token 등록
        user.setRefreshToken(refreshToken.token());
        user.setRefreshTokenExpiresAt(refreshToken.expiresAtMillis());
        userRepository.save(user);

        // Access Token & Refresh Token 응답
        response.addHeader(jwtProperties.getHeaderString(), jwtProperties.getTokenPrefix() + accessToken);

        /*
            Refresh Token은 수명이 길고, 한 번 탈취 당하는 순간(만료되기 전까지 Access Token 계속 재발급 가능) 계정이 장기간 털릴 수 있기 때문에 보다 안전하게 관리되어야 한다.
            그래서 보안 속성(httpOnly, secure, sameSite 등)을 적용할 수 있는 쿠키로 응답하는 것이다.  →  RefreshTokenCookieFactory.java 참고
            ※추가로 Refresh Token Rotation 처리도 해주면, 혹여나 탈취 당하더라도 피해를 최소화할 수 있어 보안성이 더욱 강화된다.  →  AuthController.java 참고
        */
        ResponseCookie refreshCookie = refreshTokenCookieFactory.create(refreshToken.token(), refreshToken.expiresAtMillis());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }
}