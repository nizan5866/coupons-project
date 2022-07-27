package coupon.system.core.entity_beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @Column(nullable = false)
    private String lastName;
    @NotNull
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "customer_coupon",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;


    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }



    public void setCoupons(List<Coupon> coupons){
        if(this.coupons == null){
            coupons = new ArrayList<>();
        }
        this.coupons = coupons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;

        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return 339958611;
    }
}
