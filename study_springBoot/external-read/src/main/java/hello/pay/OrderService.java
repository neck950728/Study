package hello.pay;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // https://naver.me/5FE1Fdn5 참고
public class OrderService {
    private final PayClient payClient;

    public void order(int money) {
        payClient.pay(money);
    }
}