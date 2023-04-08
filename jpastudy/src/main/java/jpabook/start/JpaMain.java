package jpabook.start;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import com.querydsl.core.Tuple;
public class JpaMain {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
    //psvm하면 자동완성
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
        Member member1 = new Member();

            member1.setUsername("회원1");
            em.persist(member1);

            Member member2 = new Member();

            member2.setUsername("회원2");
            em.persist(member2);

            Team team1 = new Team();

            team1.setName("팀1");
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);
            em.persist(team1);
            tx.commit();

    /* Movie movie = new Movie();
            movie.setDirector("감독A");
            movie.setActor("배우B");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(2000);
            em.persist(movie);
            em.flush();
            em.clear();
            em.find(Movie.class,movie.getId());

            tx.commit();
*/


               /*    for (int i = 1; i < 101; i++) {
                Member member = new Member();
                member.setUsername("member"+i);
                member.setAge(i);
                em.persist(member);            ///paging
            }
            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(0) //select할 처음 위치
                    .setMaxResults(10) //select할 개수
                    .getResultList();
            for (Member member : result) {
                System.out.println(member.getUsername());
            }*/
         /*   Member member1 = new Member();
            member1.setUsername("kim1");
            member1.setAge(20);

            Member member2 = new Member();
            member2.setUsername("kim2");
            member2.setAge(25);

            Member member3 = new Member();
            member3.setUsername("kim3");
            member3.setAge(26);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();


           List<MemberDTO> resultList = em.createQuery("select new jpabook.start.MemberDTO(m.username,m.age) from Member m "+"where m.username like '%kim%'"
                           ,MemberDTO.class).getResultList();
            MemberDTO memberDTO= resultList.get(0);
            System.out.println("memberDTO = " + memberDTO);
*/



 /*           Address address = new Address("new", "street", "zipCode");

            Member member1 = new Member();
            member1.setId("member1");
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Member member2 = new Member();
            member2.setId("member2");
            member2.setUsername("member2");
            member2.setHomeAddress(address);
            em.persist(member2);
            member1.getHomeAddress().setCity("newCity");

            tx.commit();
*/

            /*Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);



            em.persist(parent);

            em.flush();
            em.clear();
            Parent findParent = em.find(Parent.class, 1L);
            em.remove(findParent);
            //findParent.getChildList().remove(0);
            tx.commit();*/


   /*         Team team1 = new Team();
            team1.setId("team1");
            em.persist(team1);

            Team team2 = new Team();
            team2.setId("team2");
            em.persist(team2);

            Member member1 = new Member();
            member1.setId("member1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setId("member2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("=========================");
            List<Member> members = em.createQuery("select m from Member m",Member.class)
                    .getResultList();
            //select * from Member -> Member1, Member2
            tx.commit();*/


           /* Member member = new Member();
            member.setId("member1");
            member.setUsername("회원1");


            Team team1 = new Team();
            team1.setId("team1");
            team1.setName("팀1");
            em.persist(team1);


            member.setTeam(team1);
            em.persist(member);

            em.flush();
            em.clear();
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getTeam().getClass().getName());
            System.out.println("===========================");
            findMember.getTeam().getName();
            System.out.println("===========================");
*/
            /*Member m1 = em.getReference(Member.class,member.getId());
            Member m2 = em.find(Member.class,member.getId()); // 리피터블 리드 보장해주려고 find해도 프록시들고옴 find가 먼저였으면 실제 entity들고옴 getreference해도
            System.out.println("m1.getClass().getName() = " + m1.getClass().getName());
            System.out.println("m2.getClass().getName() = " + m2.getClass().getName());
            System.out.println("(m1==m2) = " + (m1==m2));//놀랍게도 jpa에서는 리피터블리드를 지켜서 true임 원래 자바에선 주소값달라서 false임
            //em.detach(m1); 이렇게 준영속상태하면 문제생김
            Hibernate.initialize(m1); // 강제 초기화
            m1.getUsername();
            tx.commit();*/

          /*  Movie movie = new Movie();
            movie.setDirector("감독A");
            movie.setActor("배우B");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(2000);
            em.persist(movie);
            em.flush();
            em.clear();
            em.find(Movie.class,movie.getId());

            tx.commit();*/

    /*        Member member1 = new Member();
            member1.setId("member1");F
            member1.setUsername("회원1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setId("member2");
            member2.setUsername("회원2");
            em.persist(member2);

            Team team1 = new Team();
            team1.setId("team1");
            team1.setName("팀1");
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);

            em.persist(team1);
            tx.commit();*/

            /*Team team1 = new Team("team1", "팀1");
            em.persist(team1);

            team1.getMembers().add(member1);
            team1.getMembers().add(member2);*/

           /* Team team1 = new Team("team1", "팀1");
            em.persist(team1);

            Member member1 = new Member();
            member1.setId("member1");
            member1.setUsername("회원1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setId("member2");

            member2.setUsername("회원2");
            em.persist(member2);

            member1.setTeam(team1);
            member2.setTeam(team1);*/

            /*Team findTeam = em.find(Team.class,"team1");
            findTeam.getMembers().stream().forEach(v-> System.out.println("v.getUsername() = " + v.getUsername()));*/


            /*Team team = new Team("team1","팀1");
            em.persist(team);

            Member member1 = new Member("member1","회원1");
            member1.setTeam(team);
            em.persist(member1);
            Member member2 = new Member("member2","회원2");
            member2.setTeam(team);
            em.persist(member2);*/
            //read
            /*Member member = em.find(Member.class, "member1");
            Team findTeam = member.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());*/
            //update
           /* Team team2 = new Team("team2","팀2");
            em.persist(team2);

            Member findMember = em.find(Member.class, "member1");
            findMember.setTeam(team2);*/
            //delete
           /* Member findMember = em.find(Member.class, "member1");
            findMember.setTeam(null);*/
          /*  Team findTeam = em.find(Team.class, "team1");
            em.remove(findTeam);*/



           /* Member member = new Member();
            member.setUsername("member2");
            member.setRoleType(RoleType.USER);
            em.persist(member);

            System.out.println("========================================"); //IDENTITY와 다르게 SEQ는 영속성에 넣고 하기때매 ===이 먼저 출력됨
            tx.commit();*/
//            Member member = createMember("memberC", "회원1");
//            member.setUsername("회원2");
//            mergeMember(member);
            /*Member findMember = em.find(Member.class, "member1");
            em.remove(findMember);
            tx.commit();*/
            /*
            Member findMember = em.find(Member.class, "id2");
            System.out.println(em.contains(findMember));
            findMember.setAge(50);
            tx.commit();*/
            /*Member member = new Member();
            member.setId("member1");
            member.setUsername("member1");
            member.setAge(20);
            System.out.println("=============Before============");
            System.out.println(em.contains(member));
            em.persist(member);
            System.out.println(em.contains(member));
            System.out.println("=============After============");
            em.flush();
            em.clear();
            Member findMember1 = em.find(Member.class, "member1");
            Member findMember2 = em.find(Member.class, "member1");
            tx.commit();*/

            /*em.persist(member); //이때 영속되지만 rollback같은 것 예외상황이 있기에 commit할때 db로 전달
            System.out.println("=============Before============");
            tx.commit(); //commit 때 DB에 날라감
            System.out.println("=============After============");*/
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }


    }
    static Member createMember(String id, String username) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        Member member = new Member();

        try {
            tx.begin();
//            member.setId(id);
//            member.setUsername(username);
//            em.persist(member);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //create하고 finally를통해 close했기에 준영속상태로 들어가서 저위에 merge로안하고 commit하면 수정안됨
        }
        //emf.close();
        return member; //close해서 member는 준영속 상태
    }
    static void mergeMember(Member member){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//            Member mergeMember = em.merge(member);
//            System.out.println("member = " + member.getUsername()); //회원명변경
//            System.out.println("mergeMember = " + mergeMember.getUsername()); //회원명변경
//            System.out.println("em.contains(member) = " + em.contains((member))); //false
//            System.out.println("em.contains(mergeMember) = " + em.contains((mergeMember))); //true
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}