package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //트랜젝션 중요(시작,종료를 적어 주어야 함)
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code를 작성
        try {
            //영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");
            //insert sql를 db에 보내지 않고 쌓음
            em.persist(member1);
            em.persist(member2);

            //이때 sql 보냄
            tx.commit();    //정상작동 시 커밋
        } catch (Exception e) {
            tx.rollback();  //문제 생기면 롤백
        } finally {
            em.close();
        }
        //애플리케이션이 끝나면 닫아줌
        emf.close();
    }
}
