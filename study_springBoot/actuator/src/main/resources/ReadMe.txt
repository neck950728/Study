● Actuator ●
- 엔드포인트란? -
쉽게 설명하자면, Actuator가 제공하는 기능 하나하나를 엔드포인트라고 한다.
즉, 개발자는 다양한 엔드포인트를 통해서 애플리케이션의 상태와 동작을 모니터링하고 관리할 수 있다.
※엔드포인트 목록 : https://docs.spring.io/spring-boot/reference/actuator/endpoints.html



- 엔드포인트 사용법 -
엔드포인트를 사용하려면 먼저 엔드포인트를 활성화하고, 그다음 노출해야 한다.

엔드포인트를 활성화한다는 것은 해당 엔드포인트 자체를 사용할지 말지(ON/OFF)를 설정하는 것이고,
노출한다는 것은 해당 엔드포인트를 어디에(웹 또는 JMX) 노출할 것인지를 설정하는 것이다.

예를 들어, beans 엔드포인트를 웹에 노출하도록 설정한 경우,
http://localhost:8080/actuator/beans 요청 시 스프링 빈으로 등록된 빈 목록이 노출된다.
※참고로 대부분의 엔드포인트는 기본적으로 활성화 상태이기 때문에 노출만 하면 된다.
　┗ 예외로, shutdown 엔드포인트는 애플리케이션을 종료시키는 엔드포인트이므로, 당연히 기본적으로 비활성화 상태이다.



- 엔드포인트 활성화 및 노출 방법(yml 기준) -
(엔드포인트 활성화)
management:
  endpoint:
    엔드포인트1:
      enabled: true
    엔드포인트2:
      enabled: true
            .
            .
            .

(엔드포인트 노출)
management:
  endpoints:
    web:
      exposure:
        include: "엔드포인트1,엔드포인트2,..."
    jmx:
      exposure:
        include: "엔드포인트1,엔드포인트2,..."

(참고 : 모두 노출)
...
  include: "*"
  exclude: "엔드포인트1,엔드포인트2,..."  ←  특정 엔드포인트 제외