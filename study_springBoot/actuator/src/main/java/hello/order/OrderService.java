package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    void order();
    void cancel();

    /*
        - AtomicInteger -
        멀티스레드 환경에서 값을 안전하게 증가/감소할 수 있음
        즉, 여러 스레드가 동시에 접근해도 안전하게 동작함
    */
    AtomicInteger getStock();
}