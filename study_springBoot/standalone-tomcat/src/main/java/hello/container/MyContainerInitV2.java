package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class) // AppInit 인터페이스 구현체들의 클래스 정보가 아래 onStartup 메서드의 파라미터 'c'로 넘어온다.
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c : " + c);
        System.out.println("MyContainerInitV2 ctx : " + ctx);

        // - 애플리케이션 초기화 -
        for(Class<?> appInitClass : c) {
            try {
                AppInit appInit = (AppInit)appInitClass.getDeclaredConstructor().newInstance(); // == new AppInitV1Servlet(), AppInitV2Spring()
                appInit.onStartup(ctx);
            }catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}