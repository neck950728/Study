package hello.order.timer.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/*
    - 메트릭(Timer) 등록 : 직접 등록 -
    Counter와 Gauge 외에도, 번외로 Timer라는 좀 특수한 메트릭도 있다.
    Timer는 다음과 같은 항목들을 한 번에 측정해 준다.

    ㆍseconds_count : 실행 시간 측정 횟수(유형 : Counter)
    ㆍseconds_sum : 실행 시간 총합(유형 : Counter)
    ㆍseconds_max : 최대 실행 시간(유형 : Gauge)
　    ┗ TimeWindow라는 개념을 사용하기 때문에 1 ~ 3분마다 최대 실행 시간이 다시 계산된다.
*/
@Slf4j
public class OrderServiceV1 implements OrderService {
    private final MeterRegistry registry;
    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV1(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void order() {
        Timer timer = Timer.builder("my.order")
                            .tag("class", this.getClass().getName())
                            .tag("method", "order")
                            .description("Metric Registration Test")
                            .register(registry);

        /*
            timer.record(new Runnable() {
                @Override
                public void run() {
                    log.info("주문");
                    stock.decrementAndGet();
                }
            });
        */
        timer.record(() -> {
            log.info("주문");
            stock.decrementAndGet();
        });
    }

    @Override
    public void cancel() {
        Timer timer = Timer.builder("my.order")
                            .tag("class", this.getClass().getName())
                            .tag("method", "cancel")
                            .description("Metric Registration Test")
                            .register(registry);

        timer.record(() -> {
            log.info("취소");
            stock.incrementAndGet();
        });
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}