package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;


    /*
        - 값 타입 컬렉션 -
        https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 : 55초 ~ 3분 14초 참고
        ※생명 주기 : 값 타입이므로, 역시 부모 Entity의 생명 주기에 의존한다. ← 즉, Cascade + 고아 객체 제거 기능을 가진다고 볼 수 있다.
    */

    @ElementCollection(fetch = FetchType.LAZY) // default fetch : 지연 로딩(LAZY)
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER_ID")) // 테이블명, JoinColumn 지정
    @Column(name = "FOOD_NAME") // https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 : 6분 34초 ~ 7분 15초 참고
    private Set<String> favoriteFoods = new HashSet<>();

    /*
        // @ElementCollection
        // @CollectionTable(name = "ADDRESS_HISTORY", joinColumns = @JoinColumn(name = "MEMBER_ID"))
        // private List<Address> addressHistory = new ArrayList<>();

        눈치가 빠른 사람이라면 JpaMain.java - '값 타입 컬렉션 수정'을 봤을 때 "값 타입 컬렉션은 웬만하면 사용하지 않는 편이 나을 것 같다."라는 느낌을 받았을 것이다.
        왜냐하면 각종 제약사항이 있고, 성능 저하(컬렉션 변동 시 매번 모두 제거 + 새로 추가)가 발생할 수 있기 때문이다.
        ※그럼, 언제 사용하는 거지? : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 40분 1초 ~ 41분 34초, 42분 32초 ~ 42분 58초 참고

        그래서 값 타입 컬렉션의 대안으로는 아래와 같은 방법을 사용한다고 한다.
        ┗ https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 : 34분 20초 ~ 36분 37초 참고
    */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();


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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    /*
        public List<Address> getAddressHistory() {
            return addressHistory;
        }

        public void setAddressHistory(List<Address> addressHistory) {
            this.addressHistory = addressHistory;
        }
    */

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
