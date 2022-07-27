package coupon.system.core.entity_beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Check;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Check(constraints = "end_date >= start_date")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "company_id")
    private Company company;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Category category;
    @NotNull
    @Column(nullable = false)
    private String title;
    private String description;
    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate endDate;
    @PositiveOrZero
    private int amount;
    @Positive
    private double price;
    private String image;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
    )
    @JoinTable(
            name = "customer_coupon",
            joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @JsonIgnore
    private List<Customer> customers;

    public Coupon(int id, Company company, Category category, String title, String description, LocalDate startDate, LocalDate endDate, int amount, double price, String image) {
        this.id = id;
        this.company = company;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public Coupon(int id) {
        this.id = id;
    }

    public Coupon(int id, Company companyID, Category category, String title, String description,
                  LocalDate startDate, LocalDate endDate, int amount, double price) {
        this.id = id;
        this.company = companyID;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
    }

    public void addCustomer(Customer customer){
        if(customers == null){
            customers = new ArrayList<>();
        }
        customers.add(customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Coupon coupon = (Coupon) o;

        return Objects.equals(id, coupon.id);
    }

    @Override
    public int hashCode() {
        return 898308180;
    }

    @Override
    public String toString() {
        return "Coupon["+ id + "]" +
                "(companyId=" + company.getId() +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                ')';
    }



}
