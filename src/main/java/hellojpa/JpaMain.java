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
            /* 멤버 저장
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);
             */
            /* 멤버 조회
            Member findmember = em.find(Member.class, 1L);
            */
            /* 멤버 수정
            Member findmember = em.find(Member.class, 1L);
            findmember.setName("helloJPA");
            //수정 후 저장은 하지 않아도 됨. jpa가 바뀐 것을 update해줌.
            */
            
            //JPQL로 쿼리를 생성할 수 있음
            //단, JPA는 테이블을 대상으로 코드를 짜는게 아니라, Member 객체를 대상으로 함!
            List<Member> result = em.createQuery("select m from Member as m", Member .class)
                    //페이징 처리
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println(member.getName());
            }

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
