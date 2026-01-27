package com.example.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final JwtProperties jwtProperties;

    public String createAccessToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withClaim("type", "access") // withClaim : 커스텀 클레임(추가적인 정보) 설정
                // .withClaim(name, value)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime()))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
    }

    public RefreshTokenBundle createRefreshToken(String username) {
        long exp = System.currentTimeMillis() + jwtProperties.getRefreshExpirationTime();
        String token = JWT.create()
                .withSubject(username)
                .withClaim("type", "refresh")
                .withExpiresAt(new Date(exp))
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
        return new RefreshTokenBundle(token, exp);
    }

    public DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(jwtProperties.getSecret())).build();
        return verifier.verify(token); // verify(검증) 실패 시 JWTVerificationException 발생  →  서명 불일치, 토큰 만료 등
    }

    /*
        ※record : https://naver.me/ID3m2lwZ 참고
        이처럼 record가 다른 클래스 내부에 정의되면, 컴파일러는 이를 static nested class로 처리한다.
        record는 외부 클래스의 인스턴스에 종속되지 않는 순수 데이터 캐리어이기 때문이다.
    */
    public record RefreshTokenBundle(String token, long expiresAtMillis) {}
}