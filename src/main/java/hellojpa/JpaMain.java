package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //트랜젝션 중요(시작,종료를 적어 주어야 함)
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code를 작성
        try {
            Member member = new Member();
            member.setId(1L);
            member.setUsername("A");
            member.setRoleType(RoleType.USER);

            em.persist(member);

            tx.commit();    //정상작동 시 커밋 -> 이때 sql 보냄
        } catch (Exception e) {
            tx.rollback();  //문제 생기면 롤백
        } finally {
            em.close();
        }
        //애플리케이션이 끝나면 닫아줌
        emf.close();
    }
}
