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
        - 연관 관계 편의 메서드 -
        ㆍ왜 굳이 양쪽 모두에 값을 세팅해야 하는 걸까? : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21698 4분 20초 ~ 10분 58초 참고
        ㆍ연관 관계 편의 메서드는 어디에 정의해야 할까? : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21698 15분 21초 ~ 17분 57초 참고
    */
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
