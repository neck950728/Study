package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
    - 임베디드 타입(사용자 정의 타입) -
    ㆍ임베디드 타입이란? : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21713 17초 ~ 3분 1초 참고
    ㆍ사용하는 이유(장점) : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21713 3분 23초 ~ 4분 19초 참고
    ※결국, 임베디드 타입은 개발의 효율성을 위해 여러 기본값 타입을 하나의 그룹으로 묶어 관리하는 것일 뿐이기 때문에 임베디드 타입을 사용한다고 해서 테이블에 변화는 없다.
　　  ┗ 쉽게 말해, 임베디드 타입을 사용하지 않았을 때와 겉모습만 다를 뿐이다.
    ※당연히 임베디드 타입은 또 다른 임베디드 타입을 포함할 수 있으며, Entity도 포함할 수 있다.
　　  ┗ 아래는 city가 Entity라고 가정했을 때의 경우다.
　　    @ManyToOne
　　    private City city; // N(Member):1(City)
*/
@Embeddable
public class Address {
    private String city;

    private String street;

    @Column(name = "ZIPCODE") // 당연히 @Column 애너테이션도 사용 가능
    private String zipcode;

    // 기본 생성자 필수
    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    /*
        ㆍ임베디드 타입의 문제점 : 임베디드 타입은 '객체'이기 때문에 여러 Entity에서 공유 시 위험하다.
　        ┗ https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21714 : 9분 48초 ~ 10분 34초 참고
        ㆍ해결 방법 : 불변 객체로 설계하면 된다.
　        ┗ https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21714 : 11분 3초 ~ 12분 3초 참고
　　　　     ┗ 내부적으로 Setter가 필요한 경우에는 Setter의 접근 제어자를 private로 변경하면 된다.
        ㆍ만약 추후에 값을 변경하고 싶다면 어떻게 해야 하나? : 새로 객체를 생성해서 값을 재설정한 후 참조하도록 해야 한다.
　        ┗ https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21714 : 14분 14초 ~ 15분 45초 참고

        public void setCity(String city) {
            this.city = city;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }
    */
}