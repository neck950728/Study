package hello;

import hello.order.gauge.StockConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

// @Import(hello.order.counter.v1.OrderConfigV1.class)
// @Import(hello.order.counter.v2.OrderConfigV2.class)
// @Import(hello.order.timer.v1.OrderConfigV1.class)
// @Import(hello.order.timer.v2.OrderConfigV2.class)
// @Import({hello.order.timer.v2.OrderConfigV2.class, StockConfigV1.class})
@Import({hello.order.timer.v2.OrderConfigV2.class, StockConfigV2.class})
@SpringBootApplication(scanBasePackages = "hello.controller")
public class ActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    // HTTP 요청/응답 history 노출 : https://inf.run/tNJBh(4초 ~ 1분 55초, 2분 35초 ~ 2분 58초) 참고
    @Bean
    public InMemoryHttpExchangeRepository httpExchangeRepository() {
        return new InMemoryHttpExchangeRepository(); // capacity 변경 방법 : setCapacity 메서드 사용
    }
}