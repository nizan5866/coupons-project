package coupon.system.core.entity_beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@NoArgsConstructor
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true,nullable = false, updatable = false)
    private String name;
    @NotNull
    @Email
    @Column(unique = true,nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Coupon> coupons;

    public Company(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Company(int id, String name, String email, String password, List<Coupon> coupons) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>(coupons);
    }

    public Company(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCoupons(List<Coupon> coupons) {
        if(this.coupons == null){
            coupons = new ArrayList<>();
        }
        this.coupons = coupons;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;

        return Objects.equals(id, company.id);
    }


    @Override
    public int hashCode() {
        return 56842787;
    }

}
