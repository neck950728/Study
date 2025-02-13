package hello.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
public class HelloController {
    @GetMapping("/hello-spring")
    public String hello() {
        System.out.println("HelloController.hello");
        return "Hello Spring!";
    }
}