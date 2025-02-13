package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

public class AppInitV1Servlet implements AppInit {
    @Override
    public void onStartup(ServletContext ctx) {
        System.out.println("AppInitV1Servlet.onStartup");

        /*
            서블릿 컨테이너에 서블릿 등록하기 ② : 프로그래밍 방식
            ※그런데 @WebServlet 애너테이션을 사용하면 편리한데, 왜 굳이 이런 방식으로 서블릿을 등록하는 걸까?
　            ┗ https://inf.run/NpgyX(6분 ~ 7분 28초) 참고
        */
        ServletRegistration.Dynamic servlet = ctx.addServlet("helloServlet", new HelloServlet());
        servlet.addMapping("/hello-servlet");
    }
}