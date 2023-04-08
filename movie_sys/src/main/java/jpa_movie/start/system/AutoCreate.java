package jpa_movie.start.system;

import jpa_movie.start.person.Address;
import jpa_movie.start.person.Customer;
import jpa_movie.start.movie.Genre;
import jpa_movie.start.movie.Movie;
import jpa_movie.start.member.Actor;
import jpa_movie.start.member.Director;
import jpa_movie.start.member.Member;
import jpa_movie.start.member.RoleType;
import jpa_movie.start.person.Staff;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AutoCreate {
    static String[] names = {"김일병","김이병","김삼병","김사병","김오병","김육병","김칠병","김팔병"
            ,"김구병","김십병","김십일병","김십이병","김십삼병","김십사병","김십오병","김십육병"};
    static String[] movieNames = {"멋진 사나이","푸른 소나무","전선을 간다","애국가"};
    static String[] directorName = {"김감독","최감독","최최감독","손감독"};
    static int ActorNameIndex = 0;
    static EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public AutoCreate(EntityManagerFactory emf){
        this.emf = emf;
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public void run(){
        try {
            tx.begin();
            List<Member> actors = autoCreateActor();
            List<Member> directors = autoCreateDirector();
            List<Movie> movies = autoCreateMovie(actors,directors);
            List<Customer> customers = autoCreateCustomer();
            List<Staff> staffs = autoCreateStaff();

            addMovieToActor(movies,actors);
            addMovieToDirector(movies,directors);

            for(int i=0; i<directors.size(); i++){ // em.persist(customers); 이렇게 리스트 바로 되는지 몰라서 일단
                em.persist(directors.get(i));
            }

            for(int i=0; i<actors.size(); i++){ // em.persist(customers); 이렇게 리스트 바로 되는지 몰라서 일단
                em.persist(actors.get(i));
            }

            for(int i=0; i<movies.size(); i++){ // em.persist(customers); 이렇게 리스트 바로 되는지 몰라서 일단
                em.persist(movies.get(i));
            }

            for(int i=0; i<customers.size(); i++){ // em.persist(customers); 이렇게 리스트 바로 되는지 몰라서 일단
                em.persist(customers.get(i));
            }
            for(int i=0; i<staffs.size(); i++){ // em.persist(customers); 이렇게 리스트 바로 되는지 몰라서 일단
                em.persist(staffs.get(i));
            }

            em.flush();

            tx.commit();

        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private List<Movie> autoCreateMovie(List<Member> actors,List<Member> directors){// 영화 자동 생성
            List<Movie> movies = new ArrayList<>();

            for(int i = 0; i <4; i++){
                Movie movie = new Movie();
                movie.setMovieName(movieNames[i]);

                for(int j =4*i; j < i*4+4;j++) {
                    movie.getMovie_member().add(actors.get(j));
                }

                movie.getMovie_member().add(directors.get(i));

                if(i%2==0){
                    movie.setGenre(Genre.ACTION);
                }else {
                    movie.setGenre(Genre.SF);
                }
                movie.setRunningTime(120+10*i);
                movie.setReleaseDate(LocalDateTime.now());
                movie.setCreatedBy("김김김");
                movie.setCreatedDate(LocalDateTime.now());
                movie.setLastModifiedBy("김김김1");
                movie.setLastModifiedDate(LocalDateTime.now().plusDays(1));
                movies.add(movie);
            }
            return movies;
    }
    private List<Member> autoCreateActor(){// 배우 자동생성

        List<Member> actors = new ArrayList<>();
        for(int i = 0;i<16;i++){
            Actor actor = new Actor();
            actor.setName(names[i]);
            actor.setBirth(19990201+i);
            actor.setHeight(160+i);
            actor.setAgency("Kumoh");
            if(i%2==0){
            actor.setRoleType(RoleType.Lead);
            }else{
                actor.setRoleType(RoleType.Supporting);
            }
            actors.add(actor);
        }
      return actors;
    }

    private List<Member> autoCreateDirector(){ //감독 자동 생성
        List<Member> directors = new ArrayList<>();

        for(int i = 0;i<4;i++){
           Director director = new Director();
            director.setName(directorName[i]);
            director.setBirth(19990201+i);
            director.setBirthplace("Daegu");
            directors.add(director);
        }
        return directors;
    }

    private List<Customer> autoCreateCustomer(){//고객 자동 생성
            int j = 0;
            List<Customer> customers = new ArrayList<>();
            for(int i=0; i<3; i++){
                Customer customer = new Customer();
                customer.setMileage(1000L+100*i);
                j++;
                String birth = "199"+j+"-0"+j+"-0"+j+" 00:00:00";
                customer.setBirth(LocalDateTime.parse(birth, formatter));
                customer.setName("김고객"+(i+1));
                customer.setAddress(new Address("서울시", "강남구", "123-12"+(i+1)));
                customer.setCreatedDate(LocalDateTime.now());
                customer.setLastModifiedDate(LocalDateTime.now());
                customers.add(customer);
            }
            
            return customers;
    }

    private List<Staff> autoCreateStaff(){//고객 자동 생성
        int j = 0;
        List<Staff> staffs = new ArrayList<>();
        for(int i=0; i<3; i++){
            Staff staff = new Staff();
            j++;
            String birth = "198"+j+"-0"+j+"-1"+j+" 00:00:00";
            staff.setBirth(LocalDateTime.parse(birth, formatter));
            staff.setName("김직원"+j);
            staff.setAddress(new Address("구미시", "옥계동"+j, "123-12"+(j)));
            String startWork = "201"+j+"-0"+j+"-1"+j+" 00:00:00";
            staff.setWorkStart(LocalDateTime.parse(startWork, formatter));
            staff.setCreatedDate(LocalDateTime.now());
            staff.setLastModifiedDate(LocalDateTime.now());
            staffs.add(staff);
        }

        return staffs;
    }


    private List<Member> addMovieToActor(List<Movie> movies, List<Member> members){

        int j =0;
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getMovie() == null){
                members.get(i).setMovie(movies.get(j));
                if((i+1)%4==0) {
                    j++;
                }
            }
        }
        return members;
    }

    private List<Member> addMovieToDirector(List<Movie> movies, List<Member> members){
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getMovie() == null){
                members.get(i).setMovie(movies.get(i));
            }
        }
        return members;
    }

}
