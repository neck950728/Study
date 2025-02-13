package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
    - Spring MVC 구성하기 -
    ※https://inf.run/tXx7c(20초 ~ 1분 18초) 참고
*/
public class AppInitV2Spring implements AppInit {
    @Override
    public void onStartup(ServletContext ctx) {
        System.out.println("AppInitV2Spring.onStartup");

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 디스패처 서블릿 생성 및 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        // 서블릿 컨테이너에 디스패처 서블릿 등록
        ServletRegistration.Dynamic servlet  = ctx.addServlet("dispatcherV1", dispatcher);
        servlet.addMapping("/spring/*");
    }
}