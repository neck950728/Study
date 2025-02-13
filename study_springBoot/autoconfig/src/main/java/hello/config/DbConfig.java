package hello.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Slf4j
/*
    알고 있다시피, @Configuration 애너테이션을 주석 처리하면
    스프링 컨테이너의 Component Scan 대상에서 제외되므로 아래 빈들은 스프링 컨테이너에 등록되지 않는다.
    그런데 신기하게도 src/test/java/hello/config/DbConfigTest.java 테스트 코드를 실행해 보면
    DataSource, TransactionManager, JdbcTemplate 모두 정상적으로 자동 주입된 것을 확인할 수 있다.

    그 이유를 아주 간략하게 설명하자면,
    스프링 부트가 개발자들이 자주 사용하는 클래스들에 대해 'Auto Configuration(자동 구성)'을 제공해 주기 때문이다.
    즉, Auto Configuration을 통해 스프링 빈으로 자동 등록되는 것이다.
    덕분에 개발자는 새로운 프로젝트를 진행할 때마다 매번 아래처럼 빈을 직접 등록해 주어야 하는 수고를 덜게 되었다.
    ※Auto Configuration이란? : https://inf.run/NjVE5(1분 5초 ~ 8분 25초) 참고
    ※자세한 내용 : 'autoconfig-test' 파트 참고
*/
// @Configuration
public class DbConfig {
    @Bean
    public DataSource dataSource() {
        log.info("dataSource 빈 등록");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:mem:test"); // 메모리 모드 : https://inf.run/fzTex(2분 25초 ~ 2분 52초) 참고
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager() {
        log.info("transactionManager 빈 등록");
        return new JdbcTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        log.info("jdbcTemplate 빈 등록");
        return new JdbcTemplate(dataSource());
    }
}