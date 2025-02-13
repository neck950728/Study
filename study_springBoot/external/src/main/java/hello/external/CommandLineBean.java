package hello.external;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/*
    5. Command Line 옵션 인수
    4번(CommandLineV2)과 동일하다.
    다만, 차이점이 있다면 ApplicationArguments의 구현체(스프링 부트가 이미 구현해서 스프링 빈으로 등록해 놓음)를 자동 주입받아 사용한다는 점이다.
*/
@Slf4j
@Component
public class CommandLineBean {
    private final ApplicationArguments arguments;

    // @Autowired // 생성자가 1개인 경우, 생략 가능
    public CommandLineBean(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @PostConstruct // 호출되는 시점 : 스프링 빈으로 등록되고, 의존성 주입까지 완료되고 난 후
    public void  init() {
        log.info("SourceArgs={}", List.of(arguments.getSourceArgs()));
        log.info("OptionNames={}", List.of(arguments.getOptionNames()));

        for(String optionName : arguments.getOptionNames()) {
            log.info("option arg {}={}", optionName, arguments.getOptionValues(optionName));
        }
    }
}