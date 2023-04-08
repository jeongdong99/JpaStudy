package jpabook.start;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Setter
@Embeddable

public class Address {
    private String city;
    private String zipCode;
    private String street;

    public Address(){

    }

    public Address(String city,String zipCode,String street){
        this.city=city;
        this.zipCode=zipCode;
       this.street=street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, zipCode, street);
    }
}
