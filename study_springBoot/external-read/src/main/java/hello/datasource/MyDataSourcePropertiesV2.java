package hello.datasource;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// - @ConfigurationProperties(생성자) -
@Getter
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV2 {
    private String url;
    private String username;
    private String password;
    private Etc etc;

    @Getter
    public static class Etc {
        private int maxConnection;
        private Duration timeout;
        private List<String> options;

        // @ConstructorBinding
        public Etc(@DefaultValue("50")int maxConnection, Duration timeout, List<String> options) {
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
        }
    }

    // @ConstructorBinding // https://inf.run/7d1LM(5분 51초 ~ 6분 40초) 참고
    public MyDataSourcePropertiesV2(String url, String username, String password, Etc etc) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.etc = etc;
    }
}