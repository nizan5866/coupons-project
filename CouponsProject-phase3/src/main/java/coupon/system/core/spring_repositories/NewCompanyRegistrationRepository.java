package coupon.system.core.spring_repositories;

import coupon.system.core.entity_beans.NewCompanyRegistration;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface NewCompanyRegistrationRepository extends JpaRepository<NewCompanyRegistration,Integer> {

    @OrderBy("dateOfRequest ASC")
    @NotNull List<NewCompanyRegistration> findAll();

    boolean existsByEmail(String email);
}
