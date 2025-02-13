package hello.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/hi-servlet") // 서블릿 컨테이너에 서블릿 등록하기 ① : 애너테이션 방식
public class HiServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
            Windows 환경에서는 별도의 설정을 해주어야 catalina.out 로그 파일이 생성되는 것 같다.
            ┗ Tomcat 폴더\bin\startup.bat 편집 : "call "%EXECUTABLE%" start..."  →  "call "%EXECUTABLE%" run %CMD_LINE_ARGS% >> ../logs/catalina.out" 수정
        */
        System.out.println("HiServlet.service");

        resp.getWriter().println("Hi Servlet!");
    }
}