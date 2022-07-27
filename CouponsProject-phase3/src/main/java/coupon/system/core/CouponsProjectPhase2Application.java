package coupon.system.core;

import coupon.system.core.coupons_exterminator2.CouponExpirationDailyJob;
import coupon.system.core.entity_beans.Category;
import coupon.system.core.security.ClientType;
import coupon.system.core.security.JwtUtil;
import coupon.system.core.security.LoginFilter;
import coupon.system.core.services.AdminService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

/**
 * @author nizan5866
 */
@SpringBootApplication
@EnableSwagger2
public class CouponsProjectPhase2Application {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CouponsProjectPhase2Application.class, args);
        CouponExpirationDailyJob couponExterminator2 =
                ctx.getBean("exterminator2",CouponExpirationDailyJob.class);
        couponExterminator2.start();
    }




    @Bean
        public FilterRegistrationBean<LoginFilter> filterRegistrationAdmin(ApplicationContext ctx) {
        JwtUtil jwtUtil = ctx.getBean(JwtUtil.class);
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("/api/admin/*");
        filterRegistrationBean.setFilter(new LoginFilter(jwtUtil, ClientType.ADMINISTRATOR));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> filterRegistrationCompany(ApplicationContext ctx) {
        JwtUtil jwtUtil = ctx.getBean(JwtUtil.class);
        LoginFilter companyFilter = new LoginFilter(jwtUtil, ClientType.COMPANY);
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("/api/company/*");
        filterRegistrationBean.setFilter(companyFilter);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> filterRegistrationCustomer(ApplicationContext ctx) {
        JwtUtil jwtUtil = ctx.getBean(JwtUtil.class);
        LoginFilter customerFilter = new LoginFilter(jwtUtil, ClientType.CUSTOMER);
        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("/api/customer/*");
        filterRegistrationBean.setFilter(customerFilter);
        return filterRegistrationBean;
    }


}