package hello.order.counter.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/*
    - 메트릭(Counter) 등록 : 직접 등록 -
    예를 들어, 주문/취소 횟수, 재고와 같은 비즈니스와 관련된 부분도 모니터링하고 싶은 경우가 있을 것이다.
    하지만 이런 비즈니스와 관련된 메트릭은 당연히 Actuator가 기본적으로 제공하지 않는다.
    즉, 메트릭을 직접 등록해 주어야 한다.
    ※목표 : https://inf.run/zzGD1(5초 ~ 36초) 참고
    ※메트릭 종류 : https://inf.run/zqRp2(3초 ~ 1분 2초) 참고
　    ┗ 이 예제에서는 주문/취소 횟수 메트릭을 등록할 것인데, 이 메트릭의 유형은 Counter이다.
*/
@Slf4j
public class OrderServiceV1 implements OrderService {
    private final MeterRegistry registry; // Micrometer는 이 MeterRegistry를 통해 메트릭을 등록/관리하고, 이를 기반으로 애플리케이션의 상태와 동작을 모니터링한다.
    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV1(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();

        // https://inf.run/zzGD1(7분 25초 ~ 8분) 참고
        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("Metric Registration Test")
                .register(registry).increment();
    }

    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("Metric Registration Test")
                .register(registry).increment();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}