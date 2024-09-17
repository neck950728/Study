package hellojpa;

import org.hibernate.Hibernate;

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
            Member member = new Member();
            member.setName("김민진");
            em.persist(member);

            em.flush();
            em.clear();

            // Member findMember = em.find(Member.class, member.getId());
            /*
                - 프록시 -
                https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21708 : 12분 14초 ~ 16분 14초 참고
                ※프록시 객체의 초기화가 발생하는 시점 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21708 10분 9초 ~ 11분 7초 참고
                 ┗ 당연한 이야기지만, 프록시 객체는 최초 한 번만 초기화된다.
                 ┗ 강제 초기화 방법(Hibernate 기준) : org.hibernate.Hibernate.initialize(프록시 객체);
            */
            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember.id : " + refMember.getId());
            System.out.println("프록시 객체 - refMember 초기화 여부 확인 : " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // 결과 : false
            System.out.println("========================================");
            System.out.println("refMember.name : " + refMember.getName());
            System.out.println("프록시 객체 - refMember 초기화 여부 확인 : " + emf.getPersistenceUnitUtil().isLoaded(refMember)); // 결과 : true

            // =======================================================================================================================================

            Member member1 = new Member();
            member1.setName("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member1");
            em.persist(member2);

            em.flush();
            em.clear();

            // 타입 비교 시 주의사항 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21708 19분 26초 ~ 24분 31초 참고
            member1 = em.find(Member.class, member1.getId());
            member2 = em.getReference(Member.class, member2.getId());
            System.out.println("member1 타입 == member2 타입 : " + (member1.getClass() == member2.getClass())); // 결과 : false

            // =======================================================================================================================================

            em.clear();

            /*
                영속성 컨텍스트에 대상 Entity가 이미 존재하는 경우, getReference 메서드를 호출해도 프록시 객체가 아닌 Entity가 반환된다.
                반대의 경우(영속성 컨텍스트에 대상 프록시 객체가 이미 존재하는 경우), 마찬가지로 find 메서드를 호출해도 Entity가 아닌 프록시 객체가 반환된다.
                ※이유 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21708 27분 23초 ~ 28분 56초, 32분 7초 ~ 34분 26초 참고
            */

            member1 = em.find(Member.class, member1.getId());
            System.out.println("member1 타입 : " + member1.getClass()); // 결과 : Member

            member1 = em.getReference(Member.class, member1.getId());
            System.out.println("member1 타입 : " + member1.getClass()); // 결과 : Member

            em.clear();

            member1 = em.getReference(Member.class, member1.getId());
            System.out.println("member1 타입 : " + member1.getClass()); // 결과 : 프록시

            member1 = em.find(Member.class, member1.getId());
            System.out.println("member1 타입 : " + member1.getClass()); // 결과 : 프록시

            // =======================================================================================================================================

            // 준영속 상태인 경우, 프록시 객체 초기화 시 "could not initialize proxy - no Session" 오류 발생
            refMember = em.getReference(Member.class, member.getId());
            em.detach(refMember); // 또는 em.clear(), em.close()
            refMember.getName(); // 프록시 객체 초기화 발생 - 오류 발생

            tx.commit();
        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}