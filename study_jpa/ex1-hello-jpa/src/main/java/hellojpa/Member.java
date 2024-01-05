package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA의 관리 대상이 되기 위한 애너테이션
// @Table(name = "USER") // 클래스명과 테이블명이 다를 경우, @Table 애너테이션을 통해 직접 지정해줄 수 있다.
public class Member {
    @Id // JPA에게 내 Primary Key가 무엇인지 알려주기 위한 애너테이션
    private Long id;
    // @Column(name = "username") // 마찬가지로 필드명과 컬럼명이 다른 경우, 지정 방법
    private String name;

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