package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        /*
            ㆍEntityManagerFactory : 하나만 생성하여 애플리케이션 전체에서 공유
            ㆍEntityManager : 일회용(DB Connection과 유사), 하나의 작업을 처리할 때 생성 후, 사용 완료 시 버려야 함(주의 : 스레드 간 공유 X)
            ※Entity : JPA에 의해 관리되는 Java 객체를 의미
        */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // hello : persistence.xml - persistence-unit 태그의 name 속성값
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        /*
            - tx.begin() ~ tx.commit() -
            JPA의 모든 데이터 변경은 트랜잭션 범위 내에서 실행되어야 한다.
        */
        tx.begin();

        try {
            /*
                보다시피 Java의 Collection을 다루는 것처럼 굉장히 편리하다.
                특히 수정할 때를 보면, 객체의 값만 변경했을 뿐인데 실제로 DB에 반영되는 것이 정말 신기하다.
                ※https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21687 : 20분 36초 ~ 23분 10초 참고
            */

            /*
                // 추가
                Member member = new Member();
                member.setId(1L);
                member.setName("HelloA");

                em.persist(member);
            */

            /*
                // 전체 조회 : JPA는 테이블이 아닌 Entity를 대상으로 하기 때문에 쿼리문이 약간 다르다.(JPQL)
                List<Member> members = em.createQuery("SELECT m FROM Member as m", Member.class).getResultList();
                for(Member member : members){
                    System.out.println("id : " + member.getId());
                    System.out.println("name : " + member.getName());
                }
            */

            // 수정
            Member member = em.find(Member.class, 1L); // 개별 조회(1L : Primary Key)
            member.setName("HelloJPA");

            // em.remove(member); // 삭제
            // em.flush(); // 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영(더티 체킹 + 버퍼(쓰기 지연 저장소) 플러시), 1차 캐시는 그대로 유지됨
            // em.clear(); // 영속성 컨텍스트 초기화(당연히 1차 캐시도 초기화됨)

            tx.commit();
        }catch(Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}