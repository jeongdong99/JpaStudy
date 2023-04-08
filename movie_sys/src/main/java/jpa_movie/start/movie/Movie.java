package jpa_movie.start.movie;

import jpa_movie.start.BaseEntity;
import jpa_movie.start.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    private String movieName;

    private LocalDateTime releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer runningTime;

    @BatchSize(size=2)
    @OneToMany(mappedBy = "movie")
    private List<Member> movie_member = new ArrayList<>();

    public List<Member> getMovie_member(){
        return movie_member;
    }
}
