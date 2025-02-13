package memory;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/*
    - Auto Configuration -
    ㆍ등록 : src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
　    ┗ 왜 Component Scan하지 않고, 위와 같은 별도의 파일에 등록해서 사용하는 것일까? : https://inf.run/8fVts(6분 42초 ~ 7분 48초), http://naver.me/FzSARDBs 참고
　　　　 ┗ 실제로 스프링 부트도 이 파일에 등록한다. : https://inf.run/tJnL8(40초 ~ 1분 11초) 참고
    ㆍ동작 방식 : http://naver.me/GOPmIeuD 참고
　    ┗ ImportSelector : https://inf.run/XjaRd(1분 7초 ~ 2분 17초, 8분 45초 ~ 9분 9초) 참고
*/

@AutoConfiguration
/*
    1. Conditional 직접 만들기 : MemoryCondition.matches 메서드의 반환값이 true인 경우(memory라는 프로퍼티의 값이 on인 경우)에만 아래 빈들이 스프링 컨테이너에 등록된다.
    @Conditional(MemoryCondition.class)
*/
@ConditionalOnProperty(name = "memory", havingValue = "on") // 2. 이미 만들어져 있는, 스프링에서 제공하는 Conditional 사용하기
public class MemoryAutoConfig {
    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }
}