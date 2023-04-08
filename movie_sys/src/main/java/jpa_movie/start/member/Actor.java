package jpa_movie.start.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("Actor")
@NoArgsConstructor
public class Actor extends Member{


    private Integer height; // 키

    private String agency; // 소속사

    @Enumerated
    private RoleType roleType; // 주연, 조연

}