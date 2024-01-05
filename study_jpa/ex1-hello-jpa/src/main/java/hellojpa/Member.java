package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA의 관리 대상이 되기 위한 애너테이션(final, enum, interface, inner 클래스에는 사용할 수 없다.)
// @Table(name = "USER") // 클래스명과 테이블명이 다를 경우, @Table 애너테이션을 통해 직접 지정해줄 수 있다.
public class Member {
    @Id // JPA에게 내 Primary Key가 무엇인지 알려주기 위한 애너테이션
    private Long id;

    // @Column(name = "username") // 마찬가지로 필드명과 컬럼명이 다른 경우, 지정 방법
    // @Column(unique = true, length = 10) // 제약 조건, 크기 등 추가적인 정보를 DDL 자동 생성 기능에 전달하기 위한 속성들(더 다양한 속성은 chapter2 참고)
    private String name;

    // DB에 저장할 필드에 final 키워드를 사용하면 안 된다.

    /*
        참고로, JPA는 내부적으로 Reflection(쉽게 말해, 동적으로 객체를 생성함)을
        사용하기 때문에 기본 생성자가 반드시 존재해야 한다.
    */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}