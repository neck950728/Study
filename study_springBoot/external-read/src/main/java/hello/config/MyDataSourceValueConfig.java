package hello.config;

import hello.datasource.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MyDataSourceValueConfig {
    // @Value 애너테이션도 내부적으로는 Environment를 통해 외부 설정값을 조회한다.
    @Value("${my.datasource.url}")
    private String url;
    @Value("${my.datasource.username}")
    private String username;
    @Value("${my.datasource.password}")
    private String password;
    @Value("${my.datasource.etc.max-connection:50}") // :50 의미 : my.datasource.etc.max-connection이라는 key가 존재하지 않는 경우, 대신 50을 기본값으로 사용하겠다.
    private int maxConnection;
    @Value("${my.datasource.etc.timeout}")
    private Duration timeout;
    @Value("${my.datasource.etc.options}")
    private List<String> options;

    @Bean
    public MyDataSource myDataSourceFromField() {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

    @Bean
    public MyDataSource myDataSourceFromParameter(
            @Value("${my.datasource.url}") String url,
            @Value("${my.datasource.username}") String username,
            @Value("${my.datasource.password}") String password,
            @Value("${my.datasource.etc.max-connection:50}") int maxConnection,
            @Value("${my.datasource.etc.timeout}") Duration timeout,
            @Value("${my.datasource.etc.options}") List<String> options) {

        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}