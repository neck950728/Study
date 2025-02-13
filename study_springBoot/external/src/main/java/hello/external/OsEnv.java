package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/*
    1. OS 환경 변수
    OS 환경 변수는 운영체제에서 실행되는 모든 프로그램에서 읽을 수 있다.
    즉, 전역 변수와 유사하다.
*/
@Slf4j
public class OsEnv {
    public static void print() {
        Map<String, String> envMap = System.getenv();
        for(String key : envMap.keySet()) {
            log.info("env {}={}", key, envMap.get(key)); // 또는 System.getenv(key)
        }
    }
}