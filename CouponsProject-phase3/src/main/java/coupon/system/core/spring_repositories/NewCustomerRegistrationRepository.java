package coupon.system.core.spring_repositories;

import coupon.system.core.entity_beans.NewCustomerRegistration;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface NewCustomerRegistrationRepository extends JpaRepository<NewCustomerRegistration, Integer> {

    @OrderBy("dateOfRequest asc")
    @NotNull List<NewCustomerRegistration> findAll();

    boolean existsByEmail(String email);

}
