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
            // - 즉시 로딩 -
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member_Eager member_eager = new Member_Eager();
            member_eager.setUsername("Member1");
            member_eager.setTeam(team);
            em.persist(member_eager);

            em.flush();
            em.clear();

            member_eager = em.find(Member_Eager.class, member_eager.getId()); // SELECT Member + Team
            System.out.println("member.team 타입 : " + member_eager.getTeam().getClass()); // 결과 : Team
            System.out.println(member_eager.getTeam().getName());

            System.out.println("======================================================================");

            // - 지연 로딩 -
            team = new Team();
            team.setName("TeamB");
            em.persist(team);

            Member_Lazy member_lazy = new Member_Lazy();
            member_lazy.setUsername("Member2");
            member_lazy.setTeam(team);
            em.persist(member_lazy);

            em.flush();
            em.clear();

            member_lazy = em.find(Member_Lazy.class, member_lazy.getId()); // SELECT Member
            System.out.println("member.team 타입 : " + member_lazy.getTeam().getClass()); // 결과 : 프록시
            System.out.println(member_lazy.getTeam().getName()); // SELECT Team(프록시 객체 초기화 발생)

            /*
                (참고)
                미니 프로젝트에서는 무엇을 사용하든 크게 상관이 없지만, 규모가 좀 있는 프로젝트나 실무에서는 즉시 로딩을 사용하지 않는 것이 좋다.
                ┗ 이유 ① : 예상치 못한 쿼리가 발생한다.
                  ┗ 자세한 내용 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21709 9분 25초 ~ 10분 15초 참고
                ┗ 이유 ② : JPQL 사용 시 N + 1 문제를 일으킨다.
                  ┗ 자세한 내용 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21709 11분 47초 ~ 15분 32초, 17분 24초 ~ 17분 39초 참고
                  ┗ 참고로 JPQL 사용 시 N + 1 문제는 즉시 로딩에서뿐만 아니라, 지연 로딩에서도 발생할 수 있는 문제다.
                    그렇지만, 지연 로딩은 프록시 덕분에 아래와 같은 특정 상황에서만 발생한다.
                    예를 들어, 모든 Member의 Team 데이터를 전부 출력해야 하는 페이지의 경우 루프를 돌려야 할 텐데,
                    이때 매 루프마다 Team 프록시 객체를 사용하는 시점에 SELECT 쿼리가 발생(프록시 객체 초기화)하므로, 이는 N + 1 문제다.
                  ┗ N + 1 문제 대안 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21709 18분 26초 ~ 20분 40초 참고
                ┗ 주의사항 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21709 21분 2초 ~ 21분 49초 참고
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