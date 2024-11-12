package hellojpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ADDRESS_HISTORY")
public class AddressEntity {
    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    public AddressEntity() {
    }

    public AddressEntity(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AddressEntity addressEntity = (AddressEntity)o;
        return Objects.equals(id, addressEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}