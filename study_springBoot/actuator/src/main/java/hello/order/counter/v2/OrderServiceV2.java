package hello.order.counter.v2;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/*
    - 메트릭(Counter) 등록 : @Counted 애너테이션 이용 -
    이전 방식(OrderServiceV1)의 문제점은 메트릭을 등록하는 로직이 비즈니스 로직에 섞여 있다는 점이다.
    이러한 문제점을 해결하기 위한 방법으로는, 가장 먼저 AOP가 떠오를 것이다.
    그래서 Micrometer는 이러한 메트릭 등록 관련 AOP 구성 요소를 이미 다 만들어 두었다.
    덕분에 개발자는 Micrometer에서 제공하는 애너테이션을 적용하기만 하면 된다.
    이 예제에서는 Counter 유형의 메트릭(주문/취소 횟수)을 등록할 것이므로, @Counted 애너테이션을 적용하면 된다.
    ※https://inf.run/WRYT5(2분 17초 ~ 2분 40초, 4분 15초 ~ 5분 22초) 참고
*/
@Slf4j
public class OrderServiceV2 implements OrderService {
    private AtomicInteger stock = new AtomicInteger(100);

    @Counted(value = "my.order")
    @Override
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
    }

    @Counted(value = "my.order")
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