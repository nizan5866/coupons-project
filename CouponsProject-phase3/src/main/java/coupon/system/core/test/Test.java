//package coupon.system.core.test;
//
//import coupon.system.core.coupons_exterminator2.CouponExpirationDailyJob;
//import coupon.system.core.exception.CouponSystemException;
//import coupon.system.core.login_manager.LoginManager;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@NoArgsConstructor
//public class Test {
//    @Autowired
//    private LoginManager loginManager;
//    @Autowired
//    CouponExpirationDailyJob couponExpirationDailyJob;
//
//
//    public void testAll() {
//        try {
//            couponExpirationDailyJob.start();
//            Tests.testForAdminFacade(loginManager);
//            Tests.testForCompanyFacade(loginManager);
//            Tests.testForCustomerFacade(loginManager);
//            couponExpirationDailyJob.stopJob();
//        } catch (CouponSystemException e) {
//            e.printStackTrace();
//        } catch (Throwable e) {
//            System.out.println(e);
//        }
//    }
//}
