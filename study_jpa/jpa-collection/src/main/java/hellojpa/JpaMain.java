package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("Kmj");
            member.setHomeAddress(new Address("city", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            /*
                member.getAddressHistory().add(new Address("old_city1", "old_street1", "old_zipcode1"));
                member.getAddressHistory().add(new Address("old_city2", "old_street2", "old_zipcode2"));
            */
            AddressEntity addressEntity1 = new AddressEntity("old_city1", "old_street1", "old_zipcode1");
            AddressEntity addressEntity2 = new AddressEntity("old_city2", "old_street2", "old_zipcode2");
            member.getAddressHistory().add(addressEntity1);
            member.getAddressHistory().add(addressEntity2);

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("====================================================================================================");

            Member findMember = em.find(Member.class, member.getId());
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            List<AddressEntity> addressHistories = findMember.getAddressHistory();

            System.out.println("====================================================================================================");

            /*
                - 값 타입 컬렉션 수정 -
                그런데 addressHistories(요소 : 임베디드 타입)는 favoriteFoods(요소 : String)랑 다르게, 왜 모두 제거한 다음 새로 추가하는 방식으로 동작하는 걸까?
                ┗ https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 : 27분 40초 ~ 28분 39초 참고

                그 이유는 아래 '값 타입 컬렉션의 제약사항' 중 1 ~ 3번 문항과 관련이 있는 것 같다.
                요약하자면, 값 타입은 고유한 식별자가 존재하지 않으므로, JPA가 값 타입 컬렉션의 개별 요소를 추적할 수 없고, 변화를 비교할 기준이 부족하기 때문이다.
                반면, String, Integer 등과 같이 일반적인 타입은 단일 값을 가지므로, 그 자체로 비교가 가능했던 것이다.
                ※값 타입 컬렉션의 제약사항 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 29분 15초 ~ 34분 10초 참고
            */

            favoriteFoods.remove("치킨");
            favoriteFoods.add("초밥");

            /*
                // 컬렉션.remove(Object) 동작 방식 참고 : https://www.inflearn.com/course/lecture?courseSlug=ORM-JPA-Basic&unitId=21716 24분 39초 ~ 25분 19초 참고
                addressHistories.remove(new Address("old_city1", "old_street1", "old_zipcode1"));
                addressHistories.add(new Address("new_city", "new_street", "new_zipcode"));
            */
            addressHistories.remove(addressEntity1);
            addressHistories.add(new AddressEntity("new_city", "new_street", "new_zipcode"));

            /*
                그런데 아래처럼 index를 활용하면 간단하게 수정할 수 있는데, 왜 굳이 위처럼 수정하는 걸까?
                ┗ addressHistories.set(0, new Address("new_city", "new_street", "new_zipcode"));

                그 이유는 간단한데, 조금 혼동했었던 것 같다.
                나는 금방 코드를 작성했으니 0번째 index에 객체가 제거할 객체와 동일하다는 것을 알 수 있지만, 사실 동등성 비교를 하지 않으면 알 수가 없다.
                즉, 값 타입은 equals, hashCode 메서드를 통해 동등성을 비교하여 동일한 객체인지를 명확히 확인해야 한다.
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