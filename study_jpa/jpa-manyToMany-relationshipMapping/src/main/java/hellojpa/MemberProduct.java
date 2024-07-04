package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    결론부터 말하자면, 다대다(N:M) 연관 관계 매핑은 사용하지 않는 것이 좋다.
    대신, 이처럼 연결 테이블을 Entity로 승격시킨 후 1:N, N:1 관계로 풀어서 사용하면 된다.
    https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21703 : 6분 14초 ~ 8분 1초 참고
*/
@Entity
public class MemberProduct {
    @Id @GeneratedValue
    private Long id; // https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21703 : 11분 53초 ~ 13분 16초 참고

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int count;
    private int price;
    private LocalDateTime orderDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}