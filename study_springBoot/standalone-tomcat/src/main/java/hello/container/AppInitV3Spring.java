package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/*
    - 서블릿 컨테이너 초기화(Spring MVC 지원) -
    어떻게 Spring MVC에서 지원하는 WebApplicationInitializer(애플리케이션 초기화) 인터페이스를 구현한 것만으로도 정상 작동하는 걸까?
    즉, 이전처럼 직접 서블릿 컨테이너 초기화 인터페이스를 구현한 다음, jakarta.servlet.ServletContainerInitializer 파일에 등록하는 과정을 생략할 수 있게 됐다.
    이게 어떻게 가능한 것인지 궁금하다면 https://inf.run/5xsAK(7분 17초 ~ 9분 10초)를 참고하길 바란다.
*/
public class AppInitV3Spring implements WebApplicationInitializer { // WebApplicationInitializer == AppInit
    @Override
    public void onStartup(ServletContext ctx) throws ServletException {
        System.out.println("AppInitV3SpringMVC.onStartup");

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 디스패처 서블릿 생성 및 스프링 컨테이너 연결
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        // 서블릿 컨테이너에 디스패처 서블릿 등록
        ServletRegistration.Dynamic servlet  = ctx.addServlet("dispatcherV2", dispatcher);
        servlet.addMapping("/");
    }
}