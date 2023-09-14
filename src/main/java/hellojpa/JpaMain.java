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
            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            
            Member member = new Member();
            member.setUsername("member1");
            //member.setTeamId(team.getId());
            member.setTeam(team); //jpa가 알아서 pk 값을 넣어줌
            em.persist(member);

            //조회
            Member findMember = em.find(Member.class, member.getId());
            //멤버의 팀을 알고 싶으면? => 멤버 클래스에서 팀 아이디를 꺼내서, 팀에서 그 아이디를 꺼냄
            //Long findTeamId = findMember.getTeamId();
            //Team findTeam = em.find(Team.class, findTeamId);
            Team findTeam = findMember.getTeam();
            //System.out.println("findTeam = "+findTeam.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
