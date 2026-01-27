package com.example.security01.config.oauth;

import com.example.security01.config.auth.PrincipalDetails;
import com.example.security01.config.oauth.provider.GoogleUserInfo;
import com.example.security01.config.oauth.provider.NaverUserInfo;
import com.example.security01.config.oauth.provider.OAuth2UserInfo;
import com.example.security01.model.User;
import com.example.security01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // oAuth2User.getAttributes() : 모든 정보 가져오기
        // oAuth2User.getAttribute(key) == oAuth2User.getAttributes().get(key) : 모든 정보 중 특정 정보만 가져오기

        String provider = userRequest.getClientRegistration().getRegistrationId(); // ex) google
        OAuth2UserInfo oAuth2UserInfo = null;
        if(provider.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(provider.equals("naver")) {
            // oAuth2User.getAttributes() 결과 : {resultcode=00, message=success, response={id=..., email=..., name=...}}
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>)oAuth2User.getAttributes().get("response"));
        }

        String providerId = oAuth2UserInfo.getProviderId(); // ex) 101446703518191279088(고유 식별자)
        String username = provider + "_" + providerId; // ex) google_101446703518191279088
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";

        // 회원으로 등록(회원가입)
        User user = userRepository.findByUsername(username);
        if(user == null) {
            user = User.builder()
                        .username(username)
                        .email(email)
                        .role(role)
                        .provider(provider)
                        .providerId(providerId)
                        .build();

            userRepository.save(user);
        }

        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
}