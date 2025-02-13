package hello.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/*
    - @ConfigurationProperties(Setter) -
    @Value 애너테이션도 충분히 편리하긴 하지만, 그래도 외부 설정값이 필요한 클래스마다 매번 적용해 주어야 한다는 불편함이 아직 조금 남아 있다.
    하지만 @ConfigurationProperties 애너테이션을 사용한 방식은 한 번만 만들어 놓으면 재사용할 수 있다.
    ※@ConfigurationProperties 애너테이션도 내부적으로는 Environment를 통해 외부 설정값을 조회한다.
    ※@ConfigurationProperties 애너테이션은 컴포넌트 스캔 대상이 아니다.
    ※정리 : https://inf.run/ivvXV(3분 48초 ~ 4분 22초) 참고
    ※문제점 : https://inf.run/ivvXV(16분 7초 ~ 17분 38초) 참고
*/
@Data
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV1 {
    private String url;
    private String username;
    private String password;
    private Etc etc; // https://inf.run/ivvXV(3분 25초 ~ 3분 45초) 참고

    @Data
    public static class Etc {
        private int maxConnection; // https://inf.run/ivvXV(10분 16초 ~ 11분 23초) 참고
        private Duration timeout;
        private List<String> options = new ArrayList<>();
    }
}