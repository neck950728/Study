package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    /*
        public void setTeam(Team team) {
            this.team = team;
        }
    */

    /*
        순수 객체 상태를 고려해서, 항상 양쪽에 값을 세팅하는 것이 좋다.
        ※https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21698 : 3분 58초 ~ 17분 23초 참고
    */
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}