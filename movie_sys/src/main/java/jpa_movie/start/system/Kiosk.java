package jpa_movie.start.system;

import jpa_movie.start.member.Member;
import jpa_movie.start.movie.Cinema;
import jpa_movie.start.movie.Movie;
import jpa_movie.start.person.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
public class Kiosk {
    private static final String LINE = "--------------------------------------------------";
    private static final String WELCOME = "Welcome to the Movie Theater!";
    private static final String MENU =
            "1. 고객과 직원 생성\n2. 영화정보 조회1\n3. 영화정보 조회2\n4. 특정 상영정보 조회\n5.예매\n6.예매정보(티켓) \n7.예매 취소 \n8.영속성 전이를 이용한 삭제 \n9.종료";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Scanner s = new Scanner(System.in);
    Cinema cinema1 = new Cinema(1,"디지털관",1,"멋진사나이","2022-11-19 12:00:00", "2022-11-19 14:00:00");
    Cinema cinema2 = new Cinema(2,"테크노관",2,"푸른소나무","2022-11-19 13:00:00", "2022-11-19 15:10:00");
    Ticketing ticketing = new Ticketing();
    public void selectMenu( EntityManagerFactory emf ) {
        while (true) {
            printMenu();
            MovieSystem movieSystem = new MovieSystem(emf);

            System.out.print("메뉴를 선택하세요 : ");
            int num = s.nextInt();
            switch (num) {
                case 1:
                    if(movieSystem.createCustomer("CreateCustomer", "구미시", "옥계동", "123-456",  LocalDateTime.parse("1999-02-19 12:12:12", formatter))){
                        System.out.println("고객 생성 성공!!");
                };
                    break;
                case 2:
                    System.out.println("Pk를 입력하세요 : ");
                    Movie movie = movieSystem.readMovieDetail((long) s.nextInt());
                    System.out.println("영화이름 : " + movie.getMovieName());
                    System.out.println("개봉일 : "+movie.getReleaseDate());
                    movie.getMovie_member().stream().forEach(v-> System.out.println("감독/배우 : " + v.getName()));
                    System.out.println("장르 : " + movie.getGenre());
                    System.out.println("러닝타임 : " + movie.getRunningTime() +"분");
                    break;
                case 3:
                    System.out.println("페이지 입력하세요 : ");
                       int page = s.nextInt();
                      List<Movie> result =  movieSystem.showMovieWithActors(page);

                      for(Movie movie1 : result){
                          System.out.println("영화 제목: " + movie1.getMovieName());
                          for(Member member : movie1.getMovie_member()){
                              System.out.println("출연 배우: "+ member.getName());
                          }
                      }
                    break;
                case 4:
                    System.out.println("상영관을 입력하세요 : ");
                    int num2 = s.nextInt();

                    switch (num2){
                        case 1 : cinema1.showScreenInfo();
                        break;
                        case 2 : cinema2.showScreenInfo();
                        break;
                    }

                    break;
                case 5:

                    if(movieSystem.movieReservation(cinema1, 31L, 3)){
                        cinema1.showScreenInfo();
                    } ;

                    break;
                case 6:

                  Ticketing ticket = movieSystem.showTicket(32L);
                    System.out.println("영화 이름: "+ ticket.getCinema().getMovieName());
                    System.out.println("관이름: " + ticket.getCinema().getCinemaName());
                    System.out.println("좌석: " + ticket.getSeatNumber() );
                    System.out.println("시작시간 : " + ticket.getCinema().getStartTime());
                    System.out.println("예매자 이름: " + ticket.getCustomer().getName());
                    System.out.println();
                    break;
                case 7:
                    if( movieSystem.cancelTicketing(cinema1,31L,3)){
                        System.out.println("예매 취소하겠습니다.");
                        cinema1.showScreenInfo();
                    }
                    break;
                case 9:
                    // 시스템 종료
                    return;

                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
    public void printWelcome() {
        System.out.println(WELCOME);
    }

    private void printMenu(){
        System.out.println(LINE);
        System.out.println(MENU);
        System.out.println(LINE);
    }
}

