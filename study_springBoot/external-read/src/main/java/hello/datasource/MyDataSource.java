package hello.datasource;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MyDataSource {
    private String url;
    private String username;
    private String password;
    private int maxConnecttion;
    private Duration timeout;
    private List<String> options;

    public MyDataSource(String url, String username, String password, int maxConnecttion, Duration timeout, List<String> options) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxConnecttion = maxConnecttion;
        this.timeout = timeout;
        this.options = options;
    }

    @PostConstruct // 호출되는 시점 : 스프링 빈으로 등록되고, 의존성 주입까지 완료되고 난 후
    public void init() {
        log.info("url={}", url);
        log.info("username={}", username);
        log.info("password={}", password);
        log.info("maxConnecttion={}", maxConnecttion);
        log.info("timeout={}", timeout);
        log.info("options={}", options);
    }
}