package jpa_movie.start.system;

import jpa_movie.start.person.Customer;
import jpa_movie.start.movie.Cinema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Ticketing { // 영화 예매를 위한 클래스
    @Id @GeneratedValue
    @Column(name = "TICKETING_ID")
    private Long id;

    @Embedded
    private Cinema cinema; // 상영관1

    @Column(name = "SEACT_NUMBER")
    private Integer seatNumber; // 좌석 번호

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer; // 고객


    public Ticketing doTicketing(Cinema cinema, Customer customer, int seatNumber){
        this.cinema = cinema;
        if (seatNumber < 0 || seatNumber > 9) {
            System.out.println("잘못된 좌석 번호 입니다.");
            return null;
        } else {
            if (!cinema.movieReservation(seatNumber)) { // 예약 실패 시 return 값은 false 인데 !false 로 true 가 됨
                System.out.println("이미 예약된 좌석 입니다.");
                return null;
            } else {
                this.customer = customer;
                this.seatNumber = seatNumber;
            }
        }
        return this;
    }

    public boolean cancelTicketing(Cinema cinema, Customer customer, int seatNumber){
        this.cinema = cinema;
        this.customer = customer;
        if(seatNumber < 0 || seatNumber > 9){
            System.out.println("잘못된 좌석 번호 입니다.");
            return false;
        }else{
            if(cinema.isSeatSelected(seatNumber)){
                return true;
            }else{
                System.out.println("예약된 좌석이 아닙니다.");
                return false;
            }
        }
    }
}
