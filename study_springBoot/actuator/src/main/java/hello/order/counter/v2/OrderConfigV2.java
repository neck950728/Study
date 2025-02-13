package hello.order.counter.v2;

import hello.order.OrderService;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV2 {
    @Bean
    public OrderService orderService() {
        return new OrderServiceV2();
    }

    /*
        CountedAspect를 스프링 빈으로 등록해 주어야 @Counted 애너테이션이 적용된 메서드에 대한 AOP가 정상 동작한다.
        ※CountedAspect 클래스를 살펴보면, 다음과 같은 Advice가 적용되어 있는 것을 볼 수 있다.
　　　　   ┗ @Around("@annotation(counted)")
    */
    @Bean
    public CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }
}