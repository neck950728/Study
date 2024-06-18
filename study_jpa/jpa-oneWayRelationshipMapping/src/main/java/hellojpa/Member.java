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
        @Column(name = "TEAM_ID")
        private Long teamId;
    */

    @ManyToOne // 다대일(N:1) 관계 - Many : Member, One : Team
    @JoinColumn(name = "TEAM_ID") // https://blog.naver.com/dngu_icdi/223483001476 참고
    private Team team; // 객체 지향적 모델링 : 이전 위(15 ~ 16 Line) 방식과는 다르게 참조하는 방식임(외래키를 직접 다루지 않음)

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}