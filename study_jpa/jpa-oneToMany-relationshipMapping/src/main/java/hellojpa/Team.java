package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /*
        결론부터 말하자면, 일대다(1:N) 연관 관계 매핑은 웬만하면 사용하지 않는다.
        ※https://inf.run/gkxhf(8분 2초 ~ 10분 15초) 참고
         ┗ 업데이트 쿼리가 발생하는 이유 : https://inf.run/gkxhf(5분 34초 ~ 6분 17초) 참고
    */
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}