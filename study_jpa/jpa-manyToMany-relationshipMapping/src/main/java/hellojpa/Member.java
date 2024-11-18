package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*
        https://inf.run/jFaz5(1분 9초 ~ 3분 2초) 참고
        ※정확히는 2개의 테이블만으로도 다대다 관계를 표현할 수 있긴 하지만, 데이터 중복, 유연성 부족 등 많은 문제점이 발생하게 된다.
　　      즉, 정규화 원칙에 위배되기도 하고, 상당히 비효율적이라 굳이 이 방식을 사용할 이유가 없다.

        @ManyToMany
        @JoinTable(name = "MEMBER_PRODUCT") // 연결 테이블명 지정(joinColumns, inverseJoinColumns 속성을 사용해서 각 외래키명도 지정할 수 있음)
        private List<Product> products = new ArrayList<>();
    */
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

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

    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }
}