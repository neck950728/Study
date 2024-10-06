package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

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

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}