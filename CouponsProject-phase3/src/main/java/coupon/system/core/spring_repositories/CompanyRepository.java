package coupon.system.core.spring_repositories;

import coupon.system.core.entity_beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.OrderBy;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    boolean existsByName(String name);




    @OrderBy("name ASC")
    @Query(
            value = "select name from company",
            nativeQuery = true
    )
    String[] findAllCompanyName();


    @Override
    @OrderBy("name ASC")
    List<Company> findAll();
}
