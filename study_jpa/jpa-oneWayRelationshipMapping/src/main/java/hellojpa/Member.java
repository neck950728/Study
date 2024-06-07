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
    /*
        외래키로 사용할 컬럼을 지정
        쉽게 말해, "내 컬럼 중 'TEAM_ID'라는 컬럼을 사용하여 Team 테이블의 PK와 JOIN하겠다."라는 의미
    */
    @JoinColumn(name = "TEAM_ID")
    private Team team; // 객체 지향적 모델링 : 이전 위 방식과는 다르게 참조하는 방식임(외래키를 직접 다루지 않음)

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