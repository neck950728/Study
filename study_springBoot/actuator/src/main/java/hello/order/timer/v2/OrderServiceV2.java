package hello.order.timer.v2;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

// - 메트릭(Timer) 등록 : @Timed 애너테이션 이용 -
@Slf4j
@Timed(value = "my.order")
public class OrderServiceV2 implements OrderService {
    private AtomicInteger stock = new AtomicInteger(100);

    // @Timed(value = "my.order")
    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
    }

    // @Timed(value = "my.order")
    @Override
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}