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
        부모 Entity와 연관 관계가 끊어진(외래키 값 : NULL) 자식 Entity를 자동으로 제거한다.
        ※cascade 옵션과 마찬가지로 소유자가 하나일 때만 사용하는 것이 좋다.
        ※@OneTo... 애너테이션에서만 동작한다.
         ┗ 참고 : One(1) = 부모, Many(N) = 자식


        (참고)
        ㆍCascadeType.REMOVE vs orphanRemoval = true
        공통점은 부모 Entity 제거 시 자식 Entity도 함께 제거된다는 것이고,
        차이점은 CascadeType.REMOVE 같은 경우, 부모 Entity에서 자식 Entity를 제외하면(childList.remove(index))
        해당 자식 Entity는 단순히 컬렉션상에서만 제외될 뿐 제거되진 않지만(단, 부모 Entity와 연관 관계가 끊어졌기 때문에 이후 Dirty Checking에 의해 고아 객체가 됨),
        orphanRemoval = true 같은 경우에는 함께 제거된다는 차이가 있다.
        
        ㆍCascadeType.ALL + orphanRemoval = true
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
