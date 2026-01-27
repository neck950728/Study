package com.example.jwt.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {
    private String secret;
    private int expirationTime;
    private int refreshExpirationTime;
    private String tokenPrefix;
    private String headerString;
    private String refreshHeaderString;
}