package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA의 관리 대상이 되기 위한 애너테이션(final, enum, interface, inner 클래스에는 사용할 수 없다.)
public class Member {
    @Id
    private Long id;

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