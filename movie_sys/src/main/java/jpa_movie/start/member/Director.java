package jpa_movie.start.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("Director")
public class Director extends Member{

    private String birthplace; // 출생지

}