package hello;

import hello.boot.MySpringApplication;
import hello.boot.MySpringBootApplication;

// 정리 : https://inf.run/NMYP5(9분 42초 ~ 10분 22초) 참고

@MySpringBootApplication
public class MySpringBootMain {
    public static void main(String[] args) {
        /*
            MySpringBootMain 클래스는 왜 Base 패키지에 둔 것이고,
            또 이전처럼 HelloConfig 클래스의 정보를 넘기는 것이 아닌, 아무것도 없는 이 클래스의 정보를 넘기는 이유는 무엇일까?
            ┗ https://inf.run/NMYP5(7분 32초 ~ 8분 49초) 참고
        */
        MySpringApplication.run(MySpringBootMain.class, args);
    }
}