package com.example.jwt.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.config.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final JwtProperties jwtProperties;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties, JwtTokenService jwtTokenService, UserRepository userRepository) {
        super(authenticationManager);
        this.jwtProperties = jwtProperties;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(jwtProperties.getHeaderString());

        // 📌 토큰이 없는 경우 📌
        if(header == null || !header.startsWith(jwtProperties.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }


        // 📌 토큰이 있는 경우 📌
        String token = header.replace(jwtProperties.getTokenPrefix(), ""); // Prefix 'Bearer '는 제거하고, 토큰만 추출
        DecodedJWT decoded = jwtTokenService.verify(token);
        String username = decoded.getSubject();
        String tokenType = decoded.getClaim("type").asString();

        /*
            실수 또는 의도적으로 Authorization 헤더에 Access Token이 아닌, Refresh Token이 전달되는 경우가 있을 수 있다.
            이때 Refresh Token도 어쨌든 토큰이기 때문에 정상적으로 검증이 성공해 버린다.
            따라서 이 경우에는 그냥 패스시킨다.
        */
        if(!"access".equals(tokenType)) {
            chain.doFilter(request, response);
            return;
        }

        // 아무 문제 없는 경우(검증 완료 & Access Token이 맞음)
        User user = userRepository.findByUsername(username);
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication); // 해당 사용자의 정보를 Authentication 객체에 담아 SecurityContext에 저장

        chain.doFilter(request, response);
    }
}