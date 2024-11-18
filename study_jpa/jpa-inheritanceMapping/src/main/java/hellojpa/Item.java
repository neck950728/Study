package hellojpa;

import javax.persistence.*;

@Entity
/*
    - 상속 관계 매핑 -
    ㆍ전략 종류 : https://inf.run/J8XYy(2분 50초 ~ 5분 16초) 참고
    ㆍ전략별 장단점 : https://inf.run/J8XYy(30분 46초 ~ 37분 8초) 참고
    ㆍ언제, 어떤 전략을 사용하는 것이 효율적일까? : https://inf.run/J8XYy(38분 17초 ~ 39분 20초) 참고
*/
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // DTYPE : 어떤 자식 테이블의 데이터인지 구분하기 위한 컬럼
public abstract class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}