package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
/*
    단어 뜻 그대로, 대상 ConfigurationProperties(MyDataSourcePropertiesV1)를 활성화하는 기능이다.
    즉, MyDataSourcePropertiesV1에 외부 설정값을 바인딩한 다음, 스프링 빈으로 등록해 준다.
    ※그런데 ConfigurationProperties를 사용하는 측에서 매번 이 애너테이션을 적용해 주어야 한다는 것은 상당히 번거롭다.
　    그래서 스프링 부트에서는 @ConfigurationPropertiesScan이라는 애너테이션을 제공한다.
　    ┗ 메인 클래스(ExternalReadApplication) 참고
*/
// @EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
public class MyDataSourceConfigV1 {
    private final MyDataSourcePropertiesV1 properties;

    // @Autowired // 생성자가 1개인 경우, 생략 가능
    public MyDataSourceConfigV1(MyDataSourcePropertiesV1 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        return new MyDataSource(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword(),
                properties.getEtc().getMaxConnection(),
                properties.getEtc().getTimeout(),
                properties.getEtc().getOptions());
    }
}