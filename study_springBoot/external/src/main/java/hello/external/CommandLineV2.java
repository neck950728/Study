package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

/*
    4. Command Line 옵션 인수
    3번(CommandLineV1)의 문제점을 해결하기 위한 방식이다.
    ┗ 스프링 부트에서 인수를 Key-value 형식으로 처리할 수 있도록 제공하는 방식이다.
    기존과는 다르게 앞에 대시 2개를 붙여 전달하면, 일반 인수가 아닌 옵션 인수로 인식한다.
*/
@Slf4j
public class CommandLineV2 {
    public static void print(String[] args) {
        // java -jar external.jar --my_url=local.db.com --my_username=local_user --my_password=local_pw mode=on
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("SourceArgs={}", List.of(appArgs.getSourceArgs())); // SourceArgs=[--my_url=local.db.com, --my_username=local_user, --my_password=local_pw, mode=on]
        log.info("OptionNames={}", appArgs.getOptionNames()); // OptionNames=[my_password, my_url, my_username]
        log.info("NonOptionArgs={}", appArgs.getNonOptionArgs()); // NonOptionArgs=[mode=on]

        Set<String> optionNames = appArgs.getOptionNames();
        for(String optionName : optionNames) {
            log.info("option arg {}={}", optionName, appArgs.getOptionValues(optionName)); // https://inf.run/et6Cn(8분 44초 ~ 9분 18초) 참고
        }
    }
}