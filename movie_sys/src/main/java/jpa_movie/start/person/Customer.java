package jpa_movie.start.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("Customer")
public class Customer extends Person {
    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long id;

    @Column(name = "CUSTOMER_MILEAGE")
    private Long mileage;

}
