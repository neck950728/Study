package com.example.security01.config.auth;

import com.example.security01.model.User;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User { // 일반 로그인 + OAuth2User 로그인 통합 처리
    private User user;
    private Map<String, Object> attributes;

    // 일반 로그인용
    public PrincipalDetails(User user) {
        this.user = user;
    }

    // OAuth2User 로그인용
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /*
        OAuth2User의 getName() 메서드는 UserDetails에서의 getUsername() 메서드와 비슷한 개념이다.
        - 일반 로그인(UserDetails) : getUsername()
        - OAuth 로그인(OAuth2User) : getName()
    */
    @Override
    public String getName() {
        return user.getUsername();
    }
}