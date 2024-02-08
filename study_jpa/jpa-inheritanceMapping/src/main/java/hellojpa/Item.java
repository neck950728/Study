package hellojpa;

import javax.persistence.*;

@Entity
/*
    Default : SINGLE_TABLE(단일 테이블)
    ※https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21705 : 2분 50초 ~ 5분 16초 참고(장단점 : 30분 46초 ~ 37분 9초)
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