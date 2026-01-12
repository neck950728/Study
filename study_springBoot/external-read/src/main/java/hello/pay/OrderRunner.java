package hello.pay;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // https://naver.me/5FE1Fdn5 참고
public class OrderRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(OrderRunner.class);
    private final OrderService orderService;

    /*
        - ApplicationRunner.run -
        이 메서드는 애플리케이션 초기화 후에 실행된다.
        즉, 스프링 컨텍스트가 모두 준비된 후(모든 빈이 준비된 상태)에 호출된다.
    */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        orderService.order(1000);
    }
}