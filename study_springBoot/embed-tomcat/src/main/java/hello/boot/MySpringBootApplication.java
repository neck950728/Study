package hello.boot;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

// 공통으로 사용되는 애너테이션들을 묶어, 간편하게 이 애너테이션만 적용하면 모두 적용되도록 하기 위한 커스텀 애너테이션이다.

// ↓ 커스텀 애너테이션 설정 : http://naver.me/Fr7pEZpK 참고
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

// ↓ 공통으로 사용되는 애너테이션들
@ComponentScan
// ...
public @interface MySpringBootApplication { }