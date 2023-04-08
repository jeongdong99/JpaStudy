package jpa_movie.start.person;


import jpa_movie.start.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Person extends BaseEntity { // 고객과 직원이 상속 하기 위한 Class

    @Id @GeneratedValue
    @Column(name = "PERSON_ID")
    private Long id; // PK

    @Column(name = "PERSON_NAME")
    private String name;

    @Column(name = "PERSON_BIRTH")
    private LocalDateTime birth;

    @Embedded
    private Address address; // Embedded From Address Class

}
