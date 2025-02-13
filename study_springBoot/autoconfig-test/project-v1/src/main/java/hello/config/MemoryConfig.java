package hello.config;

import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    이처럼 Auto Configuration을 제공하지 않는 라이브러리를 사용하면 해당 라이브러리의 클래스들을 직접 스프링 빈으로 등록해 주어야 하는 불편함이 있다.
    심지어 만약 매뉴얼까지 잘 되어 있지 않다면 어떠한 것들을 등록해 주어야 하는지 알 수 없기 때문에 직접 라이브러리 코드를 뒤져봐야 한다.
*/
@Configuration
public class MemoryConfig {
    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }
}