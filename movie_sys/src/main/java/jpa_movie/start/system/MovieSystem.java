package jpa_movie.start.system;

import jpa_movie.start.movie.Cinema;
import jpa_movie.start.movie.Movie;
import jpa_movie.start.person.Address;
import jpa_movie.start.person.Customer;
import jpa_movie.start.person.Person;
import jpa_movie.start.person.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

import static jpa_movie.start.system.SystemConstant.*;

@Getter
@Setter
@NoArgsConstructor
public class MovieSystem {

    static EntityManagerFactory emf = null;
    EntityManager em;
    EntityTransaction tx;

    public MovieSystem(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
    }

    public boolean createCustomer(String name, String city, String street, String zipcode, LocalDateTime birth){
        Customer customer = new Customer();
        try{
            tx.begin();
            customer.setMileage(0L);
            customer.setName(name);
            customer.setAddress(new Address(city, street, zipcode));
            customer.setBirth(birth);
            customer.setCreatedDate(LocalDateTime.now());
            customer.setLastModifiedDate(LocalDateTime.now());

            em.persist(customer);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updateCustomer(Object object, int update_customer_type){
        Customer customer = (Customer) object;
        try{
            tx.begin();
            switch (update_customer_type){
                case UPDATE_CUSTOMER_NAME:
                    customer.setName((String)object);
                    break;
                case UPDATE_CUSTOMER_MILEAGE:
                    customer.setMileage((Long)object);
                    break;
                case UPDATE_CUSTOMER_CITY:
                    customer.getAddress().setCity((String)object);
                    break;
                case UPDATE_CUSTOMER_STREET:
                    customer.getAddress().setStreet((String)object);
                    break;
                case UPDATE_CUSTOMER_ZIPCODE:
                    customer.getAddress().setZipcode((String)object);
                    break;
                case UPDATE_CUSTOMER_BIRTH:
                    customer.setBirth((LocalDateTime)object);
                    break;
                default:
                    return false;
            }
            customer.setLastModifiedDate(LocalDateTime.now());
            em.persist(customer);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deleteCustomer(Long customerId){
        Customer customer = em.find(Customer.class, customerId);
        try{
            tx.begin();
            em.remove(customer);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Person> readCustomer(){
        List<Person> customerList = em.createQuery("select c from Customer c", Person.class).getResultList();
        return customerList;
    }

    public Person readCustomerByName(Long customerName){
        Person customer = em.find(Person.class, customerName);
        return customer;
    }

    public boolean createStaff(String name, String city, String street, String zipcode,
                               LocalDateTime birth, LocalDateTime workStart){
        Staff staff = new Staff();
        try{
            tx.begin();
            staff.setName(name);
            staff.setAddress(new Address(city, street, zipcode));
            staff.setBirth(birth);
            staff.setWorkStart(workStart);
            staff.setCreatedDate(LocalDateTime.now());
            staff.setLastModifiedDate(LocalDateTime.now());
            em.persist(staff);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updateStaff(Object object, int update_staff_type){
        Staff staff = (Staff) object;
        try{
            tx.begin();
            switch (update_staff_type){
                case UPDATE_STAFF_NAME:
                    staff.setName((String)object);
                    break;
                case UPDATE_STAFF_WORK_START:
                    staff.setWorkStart((LocalDateTime)object);
                    break;
                case UPDATE_STAFF_CITY:
                    staff.getAddress().setCity((String)object);
                    break;
                case UPDATE_STAFF_STREET:
                    staff.getAddress().setStreet((String)object);
                    break;
                case UPDATE_STAFF_ZIPCODE:
                    staff.getAddress().setZipcode((String)object);
                    break;
                case UPDATE_STAFF_BIRTH:
                    staff.setBirth((LocalDateTime)object);
                    break;
                default:
                    return false;
            }
            staff.setLastModifiedDate(LocalDateTime.now());
            em.persist(staff);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean deleteStaff(Long staffId){
        Staff staff = em.find(Staff.class, staffId);
        try{
            tx.begin();
            em.remove(staff);
            tx.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public List<Person> readStaff(){
        List<Person> staffList = em.createQuery("select s from Staff s", Person.class).getResultList();
        return staffList;
    }

    public Person readStaffByName(Long staffName){
        Person staff = em.find(Person.class, staffName);
        return staff;
    }

    public List<Movie> readMovies(){
        List<Movie> movieList = em.createQuery("select m from Movie m", Movie.class).getResultList();
        return movieList;
    }

    public List<Movie> showMovieWithActors(int pageNum){
        int page = pageNum == 1 ? 2 : 1;
        List<Movie> movieList = em.createQuery("select m from Movie m", Movie.class)
                .setFirstResult(0)
                .setMaxResults(page)
                .getResultList();
        return movieList;
    }

    public Movie readMovieByName(String movieName){
        Movie movie = em.find(Movie.class, movieName);
        return movie;
    }
    public Ticketing showTicket(Long TicketingId){
       Ticketing ticket = em.find(Ticketing.class,TicketingId);
        return ticket;
    }
    
    public Movie readMovieDetail(Long movieId){
        Movie movie = em.find(Movie.class, movieId); // join 사용하여 Query 날리기
        return movie;
    }

    public Cinema showScreenInfoFromCinema(){ // 정재가 작성해줘야 되는 부분
        return new Cinema();
    }

    public boolean movieReservation(Cinema cinema, Long customerPk, int seatNumber){
        Ticketing ticketing = new Ticketing();
        try{
            tx.begin();
            Customer customer = em.find(Customer.class, customerPk);
            if(customer == null){
                System.out.println("not exist customer");
                return false;
            }
            if(ticketing.doTicketing(cinema, customer, seatNumber)==null){
                return false;
            };
            em.persist(ticketing);
            tx.commit();
            return true;
    }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean cancelTicketing(Cinema cinema, Long customerId, int seatNumber){
        try{
            tx.begin();
            Customer customer1 = em.find(Customer.class,customerId);

            Ticketing ticketing = em.find(Ticketing.class, 32L);
            if(ticketing==null){
                System.out.println("예매 정보가 없습니다.");
            }
            else{
                ticketing.cancelTicketing(cinema, customer1, seatNumber);
                em.remove(ticketing);
                tx.commit();
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            em.close();
        }

        return false;
    }
}
