package com.example.jwt.config.jwt;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenCookieFactory {
    private static final String COOKIE_NAME = "refreshToken";
    private static final String COOKIE_PATH = "/api/v1";
    private static final String SAME_SITE = "Lax";

    public ResponseCookie create(String refreshToken, long expiresAtMillis) {
        long now = System.currentTimeMillis();
        long maxAgeSeconds = Math.max(0, (expiresAtMillis - now) / 1000); // https://naver.me/GScbGMCb 참고

        return ResponseCookie.from(COOKIE_NAME, refreshToken)
                .httpOnly(true) // JavaScript에서 이 쿠키에 접근할 수 없게 하겠다.  →  XSS 공격으로 토큰이 탈취되는 것을 방지
                .secure(false) // true : 보안 연결(HTTPS)에서만 브라우저가 이 쿠키를 서버로 전송하게 하겠다.
                .path(COOKIE_PATH) // 해당 path 이하(/api/v1/**)의 요청에 대해서만 브라우저가 이 쿠키를 전송하게 하겠다.
                .maxAge(maxAgeSeconds)
                .sameSite(SAME_SITE) // https://naver.me/GUmJEUal 참고
                .build();
    }

    public ResponseCookie clear() {
        /*
            Q. httpOnly, secure, path, sameSite 속성은 왜 굳이 다시 설정하는 거지?
            A. 쉽게 말하자면, 발급 시와 동일한 속성으로 덮어써야 브라우저가 기존 Refresh Token 쿠키를 정확히 식별하여, 정상적으로 만료시킬 수 있기 때문이라고 한다.
        */
        return ResponseCookie.from(COOKIE_NAME, "")
                .httpOnly(true)
                .secure(false)
                .path(COOKIE_PATH)
                .maxAge(0)
                .sameSite(SAME_SITE)
                .build();
    }
}