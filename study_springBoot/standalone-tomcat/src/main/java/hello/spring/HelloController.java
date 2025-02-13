package hello.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
public class HelloController {
    /*
        해당 스프링 컨테이너와 연결된 디스패처 서블릿의 매핑 경로가 /spring/*인 경우, 아래 hello 메서드의 매핑 경로는 /spring + /hello-spring이 된다.
        즉, 앞에 /spring이 생략된 것이라고 보면 된다.
    */
    @GetMapping("/hello-spring")
    public String hello() {
        System.out.println("HelloController.hello");
        return "Hello Spring!";
    }
}