package jpa_movie.start.member;

import jpa_movie.start.movie.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "MEMBER_TYPE")
public abstract class Member { // 감독과 배우가 상속 받기 위한 Class
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    /*      공통 사항       */

    private String name; // 이름

    private Integer birth; // 생년월일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MOVIE_ID")
    private Movie movie; //출연작


    /*      공통 사항       */

}
