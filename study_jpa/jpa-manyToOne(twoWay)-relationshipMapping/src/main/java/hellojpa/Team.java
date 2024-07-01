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
        - 양방향 매핑 -
        ㆍ객체와 테이블의 양방향 연관 관계 차이 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21697 9분 8초 ~ 13분 4초 참고
        ㆍ연관 관계의 주인(Feat. mappedBy) : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21697 13분 6초 ~ 23분 13초 참고
        ㆍ양방향 매핑 시 주의사항 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21698 17분 59초 ~ 22분 41초 참고
        ㆍ양방향 매핑, 꼭 필요한가? : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21698 22분 53초 ~ 27분 27초 참고
    */
    @OneToMany(mappedBy = "team")
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
