package coupon.system.core.coupons_exterminator2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service("exterminator2")
public class CouponExpirationDailyJob {

    @Autowired
    ExtraHelper extraHelper;

    ScheduledExecutorService executorService;


    /**
     * this private method accepts minutes to wait before starting the thread
     * that uses the method inside the helper class of this package to delete
     * all coupons after their expiration date (comparing it to each day at 00:01).
     * <p>i've took this method from here: https://www.baeldung.com/java-delay-code-execution</p>
     *
     * @param timeToWaitUntilMidnightInMinutes understandable
     */
    private void deleteCouponsEveryDay(long timeToWaitUntilMidnightInMinutes) {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay
                (extraHelper::delete, timeToWaitUntilMidnightInMinutes, 1, TimeUnit.MINUTES);
    }


    /**
     * this method calculates to minutes to wait before 00:01 and delivers it
     * to the method deleteCouponsEveryDate method that initialize the thread
     * that deletes all expiration coupons every day. because it starts at
     * 00:01 it is very precise.
     */
    public void start() {
        System.out.println("~*~*~*~*~*~*~*~*~*~*Coupon Exterminator program has initialized*~*~*~*~*~*~*~*~*~*~");
        int hourToWait = 23 - LocalTime.now().getHour();
        int minutesToWait = 61 - LocalTime.now().getMinute();
        long timeToWaitUntilMidnightInMinutes = hourToWait * 60 + minutesToWait;
        deleteCouponsEveryDay(timeToWaitUntilMidnightInMinutes);
    }

    /**
     * this method is used to stop the executorService thread
     */
    public void stopJob() {
        executorService.shutdown();
        System.out.println("~*~*~*~*~*~*~*~*~*~*Coupon Exterminator program Shut Down*~*~*~*~*~*~*~*~*~*~*~*~*~");
    }
}
