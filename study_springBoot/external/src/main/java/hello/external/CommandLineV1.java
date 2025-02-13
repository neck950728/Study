package hello.external;

import lombok.extern.slf4j.Slf4j;

/*
    3. Command Line 인수
    Java 애플리케이션 실행 시점에 main 메서드의 인수(String[] args)로 값을 전달하는 방식이다.
    ※문제점 : https://inf.run/xMG6N(3분 6초 ~ 4분 49초) 참고
*/
@Slf4j
public class CommandLineV1 {
    public static void print(String[] args) {
        // java -jar external.jar my_url=local.db.com my_username=local_user my_password=local_pw
        for(String arg : args) {
            log.info("arg {}", arg);
        }
    }
}