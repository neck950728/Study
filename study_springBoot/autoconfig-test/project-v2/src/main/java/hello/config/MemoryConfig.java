package hello.config;

import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    웹 애플리케이션을 실행하고 라이브러리를 테스트(http://localhost:8080/memory)해 보면
    아래 설정이 주석 처리되어 있음에도 불구하고 라이브러리가 정상 동작하는 것을 볼 수 있다.
    이처럼 Auto Configuration을 제공하는 라이브러리를 사용하면 별다른 설정 없이 바로 라이브러리를 사용할 수 있다!

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
*/