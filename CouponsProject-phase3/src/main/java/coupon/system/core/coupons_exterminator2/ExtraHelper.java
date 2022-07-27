package coupon.system.core.coupons_exterminator2;

import coupon.system.core.spring_repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ExtraHelper {

    @Autowired
    CouponRepository couponRepository;

    //TODO maybe autowire the coupon repository to here

    /**
     * i have to use this extra helper so i can use the Thread because the other class cant be
     * transactional.
     */
    public void delete(){
        couponRepository.deleteAllByEndDateBefore(LocalDate.now());
    }
}
