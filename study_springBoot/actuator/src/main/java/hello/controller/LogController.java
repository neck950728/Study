package hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {
    @GetMapping("/log")
    public void log() {
        /*
            로그 레벨을 DEBUG로 설정했기 때문에 DEBUG와 더 높은 레벨인 INFO, WARN, ERROR가 출력된다.
            ※TRACE < DEBUG < INFO < WARN < ERROR
        */
        log.trace("trace log");
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
    }
}