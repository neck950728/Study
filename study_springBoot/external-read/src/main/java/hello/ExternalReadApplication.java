package hello;

import hello.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

// ↓ https://inf.run/2B6HC(13분 11초 ~ 14분 37초) 참고
// @Import(MyDataSourceEnvConfig.class)
// @Import(MyDataSourceValueConfig.class)
// @Import(MyDataSourceConfigV1.class)
// @Import(MyDataSourceConfigV2.class)
@Import(MyDataSourceConfigV3.class)
@SpringBootApplication(scanBasePackages = {"hello.datasource", "hello.pay"})
@ConfigurationPropertiesScan // https://inf.run/ivvXV(11분 31초 ~ 13분 49초) 참고
public class ExternalReadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }
}