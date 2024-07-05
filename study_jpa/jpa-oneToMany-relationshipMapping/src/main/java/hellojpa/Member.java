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
        - 일대다(1:N) 양방향 매핑 -
        ㆍhttps://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21701 : 14분 18초 ~ 18분 2초 참고
        ㆍhttps://blog.naver.com/dngu_icdi/223483002064 참고
    */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
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

    public void setTeam(Team team) {
        this.team = team;
    }
}