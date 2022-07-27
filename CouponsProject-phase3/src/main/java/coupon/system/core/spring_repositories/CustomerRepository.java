package coupon.system.core.spring_repositories;

import coupon.system.core.entity_beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findByEmailAndPassword(String email, String password);

    public boolean existsByEmail(String email);
}
