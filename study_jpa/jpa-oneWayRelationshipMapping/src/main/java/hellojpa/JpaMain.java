package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            em.flush(); // 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영(더티 체킹 + 버퍼(쓰기 지연 저장소) 플러시), 1차 캐시는 그대로 유지됨
            em.clear(); // 영속성 컨텍스트 초기화(당연히 1차 캐시도 초기화됨)

            Member findMember = em.find(Member.class, member.getId());
            // Team findTeam = em.find(Team.class, findMember.getTeamId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam : " + findTeam.getName());

            /*
                // Update(팀 변경) : DB의 외래키 값이 변경됨
                Team newTeam = em.find(Team.class, 100L);
                member.setTeam(newTeam);
            */

            tx.commit();
        }catch(Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}