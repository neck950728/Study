package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// - 메트릭(Gauge) 등록 : 방법 ② -
@Slf4j
@Configuration
public class StockConfigV2 {
    @Bean
    public MeterBinder meterBinder(OrderService orderService) {
        /*
            return new MeterBinder() {
                @Override
                public void bindTo(MeterRegistry registry) {
                    Gauge.builder("my.stock", orderService, new ToDoubleFunction<OrderService>() {
                        @Override
                        public double applyAsDouble(OrderService service) {
                            log.info("stock gauge call");
                            return service.getStock().get();
                        }
                    }).register(registry);
                }
            };
        */
        return registry -> Gauge.builder("my.stock", orderService, service ->{
            log.info("stock gauge call");
            return service.getStock().get();
        }).register(registry);
    }
}