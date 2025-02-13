package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// - 메트릭(Gauge) 등록 : 방법 ① -
@Configuration
public class StockConfigV1 {
    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry registry) {
        return new MyStockMetric(orderService, registry);
    }

    @Slf4j
    static class MyStockMetric {
        private OrderService orderService;
        private MeterRegistry registry;

        public MyStockMetric(OrderService orderService, MeterRegistry registry) {
            this.orderService = orderService;
            this.registry = registry;
        }

        /*
            @PostConstruct
            public void init() {
                Gauge.builder("my.stock", orderService, new ToDoubleFunction<OrderService>() {
                    @Override
                    public double applyAsDouble(OrderService service) {
                        log.info("stock gauge call");
                        return service.getStock().get();
                    }
                }).register(registry);
            }
        */
        @PostConstruct // 호출되는 시점 : 스프링 빈으로 등록되고, 의존성 주입까지 완료되고 난 후
        public void init() {
            // https://inf.run/2ZaSA(2분 56초 ~ 3분 37초, 6분 24초 ~ 7분 42초) 참고
            Gauge.builder("my.stock", orderService, service -> {
                log.info("stock gauge call");
                return service.getStock().get();
            }).register(registry);
        }
    }
}