package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*
        외래키는 주 테이블(더 자주 사용되는 테이블)에 두는 것이 개발자 입장에서는 더 유리하다.
        하지만 외래키에 NULL 값을 허용해야 하므로 DBA 입장에서는 마음에 들지 않을 것이다.
        그러므로 DBA와 충분히 상의 후 결정해야 한다.
        ※자세한 내용 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21702 8분 54초 ~ 12분 45초 참고
        ※외래키 위치에 따른 장단점 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21702 15분 5초 ~ 18분 30초 참고
    */
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

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

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }
}
