package jpa_movie.start.movie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;

//예매 내역만 디비에 넣어주고 나머진 그냥 프로젝트에 데이터 적재 남은자리와 시간 등은 수동으로 값넣어주기
@Getter
@Setter
@Embeddable
public class Cinema {
    private long screenPK;
    //상영관이름
    private String CinemaName;
    //상영관층수
    private int floor;
    //영화
    private String movieName;
    //시작시간,종료시간
    //이건 db랑 맞춰서 그냥 값넣어주기
    private String startTime;
    private String endTime;

    //자리
    boolean seat[][] = new boolean[2][5];

    public Cinema() {

    }

    public Cinema(long screenPK,String name, int floor, String movieName, String startTime, String endTime){
        for(int i = 0; i < 2; i++){
            for(int j = 1; j < 5; j+=2){
                seat[i][j] = true;
            }
        }
        this.screenPK = screenPK;
        this.CinemaName = name;
        this.floor = floor;
        this.movieName = movieName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //현재 상영관의 남은 자리 표시
   public void showScreenInfo(){
        System.out.println("영화 이름: " + movieName);
        System.out.println("시작시간: " + startTime);
        System.out.println("종료시간: " + endTime);
       System.out.println("총 좌석:" + seat.length*seat[0].length);

        int possible = 0;
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j ++) {
                if (seat[i][j] == false) {
                    System.out.print("X ");
                }
                else{
                    possible++;
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
       System.out.println("가능 좌석:" + possible);
    }

    //자리예매 ppt에선 예제대로 하지말고 보면 알듯이 수입력해서 예매하는 방법 예매 되면 리턴넣어서 true이면 db에 예매 내역넣는 방식으로
    public boolean movieReservation(int ticketingId){
        int col = ticketingId /5;
        int row = ticketingId % 5;
        if(seat[col][row] == false){
            System.out.println("예약 할 수 없는 자리입니다.다시 확인해주세요");
            return false;
        }else{
            seat[col][row] = false;
        }
        return true;
    }

    public void isMovieCancelled(int cancelId){
        int col = cancelId /5;
        int row = cancelId % 5;
        if(seat[col][row] == false){
            seat[col][row] = true;
        }else
            System.out.println("이미 예약이 취소된 자리 입니다.");
    }



    public boolean isSeatSelected(int seatNumber){
        // 좌석 선택 됐으면 return true;
        // 좌석이 비어 있으면 return false;
        if(seatNumber <5){
            if(seatNumber%2 == 0){
                System.out.println("예매한 자리가 아닙니다");
                return false;
            }
        }else{
            if(seatNumber%2 == 1){
                System.out.println("예매한 자리가 아닙니다");
                return false;
            }
        }
        int col = seatNumber /5;
        int row = seatNumber % 5;
        if(seat[col][row] == false){
            seat[col][row] = true;
            return true;
        }else
            return false;
    }

}
