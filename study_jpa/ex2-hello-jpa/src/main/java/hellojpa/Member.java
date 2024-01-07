package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
// @Table(name = "USER") // 클래스명과 테이블명이 다를 경우, @Table 애너테이션을 통해 직접 지정해줄 수 있다.
public class Member {
    /*
        - @Id -
        개발자가 ID 값을 직접 지정하는 경우 사용

        - @GeneratedValue(strategy = ...) -
        자동 생성(증가) 방식, 간략하게 설명하자면 다음과 같다.
        ㆍGenerationType.AUTO : JPA가 IDENTITY, SEQUENCE, TABLE 중 알맞은 것을 알아서 선택함
        ㆍGenerationType.IDENTITY : Auto Increment를 사용하는 DB(MySQL, PostgreSQL, SQL Server, DB2 등)인 경우 사용
        ㆍGenerationType.SEQUENCE : Sequence를 사용하는 DB(Oracle DB, PostgreSQL, DB2, H2 DB 등)인 경우 사용
        ㆍGenerationType.TABLE : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21694 12분 7초 ~ 15분 48초 참고
        ※IDENTITY 특이사항 : https://blog.naver.com/dngu_icdi/223314403168 참고
        ※성능 관련 이야기 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21694 30분 37초 ~ 37분 30초
    */
    @Id // JPA에게 내 Primary Key가 무엇인지 알려주어야 하므로, @GeneratedValue 애너테이션을 사용한다 한들 필요하다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name") // 마찬가지로 필드명과 컬럼명이 다를 경우, 지정 방법
    /*
        @Column(nullable = false, length = 10, ...) : name 외 속성
        DDL 자동 생성 기능 사용 시 제약 조건, 크기 등의 추가적인 정보를 전달
        ※https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21693 : 6분 ~ 10분 32초 참고
    */
    private String username;

    private Integer age;

    // https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21693 : 10분 35초 ~ 16분 10초 참고
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /*
        ㆍTemporalType.DATE : 날짜
        ㆍTemporalType.TIME : 시간
        ㆍTemporalType.TIMESTAMP : 날짜 + 시간
        ※DB에서는 날짜 및 시간 데이터를 세 가지(DATE, TIME, TIMESTAMP) 타입으로 구분한다.
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate; // Java Date 타입 : 날짜 + 시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /*
        Java 8 버전 이상부터는 LocalDate, LocalDateTime 클래스를 제공하기 때문에
        굳이 @Temporal 애너테이션을 사용할 필요가 없어졌다.
    */
    private LocalDate localDate; // LocalDate : DATE 타입으로 매핑됨
    private LocalDateTime localDateTime; // LocalDateTime : TIMESTAMP 타입으로 매핑됨

    @Lob // Large Object : 문자 타입은 CLOB, 그 외 타입은 BLOB로 매핑된다.
    private String description;

    @Transient // DB랑 관련 없는 일반 필드로 사용할 경우 적용
    private String hello;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}