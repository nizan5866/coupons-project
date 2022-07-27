package coupon.system.core.coupons_exterminator1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Service("exterminator1")
public class CouponExpirationDailyJob1 extends Thread {

    @Autowired
    private CouponExpirationDailyJobHelper1 couponExpirationDailyJobHelper1;
    private boolean quit;



    /**
     * this method calculates to minutes to wait before 00:01
     * then if the "quit" field is false, it starts deleting all
     * expired coupons, and repeat doing so every single day
     */
    @Override
    public void run() {
        System.out.println("**********Coupon Exterminator program has initialized**********");
        int hourToWait = 23 - LocalTime.now().getHour();
        int minutesToWait = 61 - LocalTime.now().getMinute();
        long timeToWaitUntilMidnightInMinutes = hourToWait * 60 + minutesToWait;
        try {
            TimeUnit.MINUTES.sleep(timeToWaitUntilMidnightInMinutes);
            while (true) {
                if (!quit) {
                    couponExpirationDailyJobHelper1.deleteExpCoupons();
                } else {
                    break;
                }
                TimeUnit.DAYS.sleep(1);
            }
        } catch (InterruptedException e) {
            System.out.println("############ Coupon Exterminator program Shut Down Dou to Interruption ###########");
        }
    }

    /**
     * this method is used to stop thread
     */
    public void stopJobWhenDayEnds() {
        quit = true;
        System.out.println("**********Coupon Exterminator program Shut Down*************~");
    }

    public void stopJobNow(){
        this.interrupt();
    }

}