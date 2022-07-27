package coupon.system.core.entity_beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewCompanyRegistration {

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
    @Column(nullable = false, length = 2600)
    private String about;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateOfRequest;

    public NewCompanyRegistration(String name, String email, String password, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }
}
