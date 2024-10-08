package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /*
        - cascade(영속성 전이) -
        https://blog.naver.com/dngu_icdi/223587182319 참고


        - orphanRemoval(고아 객체 제거) -
        부모 Entity와 연관 관계가 끊어진 자식 Entity를 자동으로 제거한다.
        ※cascade 옵션과 마찬가지로 소유자가 하나일 때만 사용하는 것이 좋다.
        ※@OneTo... 애너테이션에서만 동작한다.
         ┗ 참고 : One(1) = 부모, Many(N) = 자식


        (참고)
        ㆍCascadeType.REMOVE vs orphanRemoval = true
        공통점은 부모 Entity 제거 시 자식 Entity도 함께 제거된다는 것이고,
        차이점은 CascadeType.REMOVE 같은 경우, 부모 Entity에서 자식 Entity를 제외하면(childList.remove(index))
        해당 자식 Entity가 단순히 컬렉션상에서만 제외될 뿐 제거되진 않지만(Delete 쿼리가 발생하지 않음),
        ┗ 참고로 여기서 "어라, 더티 체킹으로 인해 변경 내용이 반영되지 않나?"라는 생각을 할 수도 있을 텐데, 더티 체킹은 필드 값 자체가 변경된 경우에만 감지할 수 있다.
        orphanRemoval = true 같은 경우에는 제거된다는 차이가 있다.

        ㆍCascadeType.REMOVE + orphanRemoval = true
        https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21710 : 20분 2초 ~ 22분 32초 참고
    */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

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

    public List<Child> getChildList() {
        return childList;
    }

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
    }
}