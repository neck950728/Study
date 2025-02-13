package hello.order.timer.v2;

import hello.order.OrderService;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV2 {
    @Bean
    public OrderService orderService() {
        return new OrderServiceV2();
    }

    // 마찬가지로 TimedAspect를 스프링 빈으로 등록해 주어야 @Timed 애너테이션이 적용된 메서드에 대한 AOP가 정상 동작한다.
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}