package jpa_movie.start.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("Staff")
public class Staff extends Person {
    @Id @GeneratedValue
    @Column(name = "STAFF_ID")
    private Long id;

    @Column(name = "STAFF_WORKSTART")
    private LocalDateTime workStart;

}
