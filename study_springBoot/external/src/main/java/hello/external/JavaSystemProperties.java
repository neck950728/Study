package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/*
    2. Java 시스템 속성
    Java 애플리케이션 실행 시점에 설정한 속성값을 가져오는 방식이다.
*/
@Slf4j
public class JavaSystemProperties {
    public static void print() {
        Properties properties = System.getProperties();
        for(Object key : properties.keySet()) {
            log.info("prop {}={}", key, System.getProperty(String.valueOf(key)));
        }

        // java -Dmy_url=local.db.com -Dmy_username=local_user -Dmy_password=local_pw -jar external.jar
        log.info("my_url={}", System.getProperty("my_url"));
        log.info("my_username={}", System.getProperty("my_username"));
        log.info("my_password={}", System.getProperty("my_password"));
    }
}