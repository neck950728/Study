package hello.external;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/*
    6. 통합
    스프링 부트는 왜 지금까지 배운 방식들을 하나로 통합한 것이고, 어떤 방식으로 통합하였을까? : https://inf.run/gDME1(4초 ~ 2분 35초, 3분 37초 ~ 6분 52초) 참고
    ┗ 테스트 : https://inf.run/gDME1(8분 27초 ~ 9분 37초) 참고
    ┗ 전체 우선순위 : https://inf.run/8Hgsq(1분 14초 ~ 4분 12초, 5분 3초 ~ 8분 29초) 참고
*/
@Slf4j
@Component
public class Integration {
    private final Environment env;

    public Integration(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void init() {
        log.info("env my_url={}", env.getProperty("my_url"));
        log.info("env my_username={}", env.getProperty("my_username"));
        log.info("env my_password={}", env.getProperty("my_password"));
    }
}