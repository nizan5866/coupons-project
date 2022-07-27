package coupon.system.core.coupons_exterminator1;

import coupon.system.core.spring_repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class CouponExpirationDailyJobHelper1 {

    @Autowired
    private CouponRepository couponRepository;


    /**
     * this method is for the use of the run method. the reason it is seperated
     * is so i can make an easy Transactional annotation
     */
    public void deleteExpCoupons() {
        couponRepository.deleteAllByEndDateBefore(LocalDate.now());
    }
}
