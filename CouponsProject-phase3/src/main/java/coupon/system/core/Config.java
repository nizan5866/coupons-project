package coupon.system.core;

import coupon.system.core.entity_beans.*;
import coupon.system.core.services.AdminService;
import coupon.system.core.services.CompanyService;
import coupon.system.core.services.CustomerService;
import coupon.system.core.services.GeneralService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class Config {

    private LocalDate yearBeginning;
    private LocalDate yearEnding;

    {
        int year = LocalDate.now().getYear();
        this.yearBeginning = LocalDate.parse(year+"-01-01");
        this.yearEnding = LocalDate.parse(year+"-12-31");
    }

    //=================================================================================================================
    final Company COMPANY1 = new Company(1, "Apple", "apple@gmail.com", "1234");
    final Company COMPANY2 = new Company(2, "Google", "google@gmail.com", "1234");
    final Company COMPANY3 = new Company(3, "Amazon", "amazon@gmail.com", "1234");
    final Company COMPANY4 = new Company(4, "Microsoft", "microsoft@gmail.com", "1234");
    final Company COMPANY5 = new Company(5, "Sony", "sony@gmail.com", "1234");


    //=====================================================================================================

    final Coupon COUPON1 = new Coupon(1, COMPANY1, Category.SMARTPHONES, "20% off smartphones", "get 20% discount on smartphones",yearBeginning, yearEnding, 5, 100, "@@@");
    final Coupon COUPON2 = new Coupon(2, COMPANY1, Category.COMPUTERS, "20% off computers", "get 20% discount on computers", yearBeginning, yearEnding, 5, 200, "@@@");
    final Coupon COUPON3 = new Coupon(3, COMPANY1, Category.CLOUD_STORAGE, "50Gb of cloud storage", "get cloud Storage", yearBeginning, yearEnding, 5, 300, "@@@");

    final Coupon COUPON4 = new Coupon(4, COMPANY2, Category.SMARTPHONES, "20% off smartphones", "get 20% discount on smartphones", yearBeginning, yearEnding, 5, 300, "@@@");
    final Coupon COUPON5 = new Coupon(5, COMPANY2, Category.COMPUTERS, "20% off computers", "get 20% discount on computers", yearBeginning,yearEnding, 5, 100, "@@@");
    final Coupon COUPON6 = new Coupon(6, COMPANY2, Category.CLOUD_STORAGE, "5Gb cloudStorage", "get cloud Storage", yearBeginning, yearEnding, 5, 200, "@@@");

    final Coupon COUPON7 = new Coupon(7, COMPANY3, Category.CLOUD_STORAGE, "20% off cloud space", "get 20% discount on cloud space", yearBeginning, yearEnding, 1, 50, "@@@");
    final Coupon COUPON8 = new Coupon(8, COMPANY3, Category.SOFTWARE, "amazon prime", "one month of amazing prime", yearBeginning, yearEnding, 5, 30, "@@@");
    final Coupon COUPON9 = new Coupon(9, COMPANY3, Category.SOFTWARE, "50$ gift card", "get 50$ gift card for amazon", yearBeginning, yearEnding, 5, 45, "@@@");

    final Coupon COUPON10 = new Coupon(10, COMPANY4, Category.SOFTWARE, "20% off office", "get 20% discount on office", yearBeginning, yearEnding, 5, 149.9, "@@@");
    final Coupon COUPON11 = new Coupon(11, COMPANY4, Category.SOFTWARE, "ONLY EXCEL", "get excel desktop program", yearBeginning, yearEnding, 5, 49.9, "@@@");
    final Coupon COUPON12 = new Coupon(12, COMPANY4, Category.COMPUTERS, "20% off computers", "get 20% discount on computers", yearBeginning, yearEnding, 5, 79.9, "@@@");
    final Coupon COUPON13 = new Coupon(13, COMPANY4, Category.SOFTWARE, "windows for students", "get windows for students at a major discount", yearBeginning, yearEnding, 5, 300.99, "@@@");

    final Coupon COUPON14 = new Coupon(14, COMPANY5, Category.SOFTWARE, "workshop for movies", "get our new workshop for movies!", yearBeginning, yearEnding, 5, 200, "@@@");
    final Coupon COUPON15 = new Coupon(15, COMPANY5, Category.SOFTWARE, "workshop for photos", "get our new workshop for photos!", yearBeginning, yearEnding, 20, 200, "@@@");
    final Coupon COUPON16 = new Coupon(16, COMPANY5, Category.SOFTWARE, "workshop for songs", "get our new workshop for songs!", yearBeginning, yearEnding, 20, 200, "@@@");
    final Coupon COUPON17 = new Coupon(17, COMPANY5, Category.SOFTWARE, "get out new spiderman movie!", "get our new spiderman movie!", yearBeginning, yearEnding, 300, 1.99, "@@@");
    final Coupon COUPON18 = new Coupon(18, COMPANY5, Category.SMARTPHONES, "10% on our smartphones!", "get 10% discount on our best top smartphones!", yearBeginning, yearEnding, 5, 100, "@@@");
    final Coupon COUPON19 = new Coupon(19, COMPANY5, Category.SMARTPHONES, "20% on our smartphones!", "get 20% discount on our best top smartphones!", yearBeginning, yearEnding, 5, 170, "@@@");
    final Coupon COUPON20 = new Coupon(20, COMPANY5, Category.SMARTPHONES, "30% on our smartphones!", "get 30% discount on our best top smartphones!", yearBeginning, yearEnding, 5, 300, "@@@");
    final Coupon COUPON21 = new Coupon(21, COMPANY5, Category.SMARTPHONES, "40% on our smartphones!", "get 40% discount on our best top smartphones!", yearBeginning, yearEnding, 5, 400, "@@@");
    final Coupon COUPON22 = new Coupon(22, COMPANY5, Category.SMARTPHONES, "50% on our smartphones!", "get 50% discount on our best top smartphones!", yearBeginning, yearEnding, 5, 550, "@@@");
    final Coupon COUPON23 = new Coupon(23, COMPANY5, Category.COMPUTERS, "15% on our computers!", "get 15% discount on our best top computers!", yearBeginning, yearEnding, 5, 178.89, "@@@");
    final Coupon COUPON24 = new Coupon(24, COMPANY5, Category.COMPUTERS, "get one computer!", "get one computer up to the price of 1500$!", yearBeginning, yearEnding, 5, 1400, "@@@");
    final Coupon COUPON25 = new Coupon(25, COMPANY5, Category.COMPUTERS, "get a random present from us!", "that's right, a free present from us!", yearBeginning, yearEnding, 5, 0.1, "@@@");



    //=====================================================================================================

    final Customer CUSTOMER1 = new Customer(1, "Avraham", "Cohen", "avrahamcohen@gmail.com", "1234");
    final Customer CUSTOMER2 = new Customer(2, "Beny", "Gabbay", "benygabbay@gmail.com", "1234");
    final Customer CUSTOMER3 = new Customer(3, "Dana", "Cohen", "danacohen@gmail.com", "1234");
    final Customer CUSTOMER4 = new Customer(4, "Fiona", "Floochi", "fionafloochi@gmail.com", "1234");

    //=======================================================================================================

    final NewCustomerRegistration NEWCUSTOMERREGISTRATION1 = new NewCustomerRegistration("Leonard","Cohen","amIgettingAOrWhat@gmail.com","1234");
    final NewCustomerRegistration NEWCUSTOMERREGISTRATION2 = new NewCustomerRegistration("Elvis","Presley","theking@gmail.com","1234");
    final NewCustomerRegistration NEWCUSTOMERREGISTRATION3 = new NewCustomerRegistration("John","Smith","youKnowWhoIAm@gmail.com","1234");

    final NewCompanyRegistration NEWCOMPANYREGISTRATION1 = new NewCompanyRegistration("WaltDisney","thelionking@gmail.com","1234","we love making merchandising");



    @Bean
    public CommandLineRunner superDuper(ApplicationContext ctx) {
        AdminService adminService = ctx.getBean(AdminService.class);
        CompanyService companyService = ctx.getBean(CompanyService.class);
        CustomerService customerService = ctx.getBean(CustomerService.class);
        GeneralService generalService = ctx.getBean(GeneralService.class);
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                generalService.companyRegistrationRequest(NEWCOMPANYREGISTRATION1);

                generalService.customerRegistrationRequest(NEWCUSTOMERREGISTRATION1);
                generalService.customerRegistrationRequest(NEWCUSTOMERREGISTRATION2);
                generalService.customerRegistrationRequest(NEWCUSTOMERREGISTRATION3);

                adminService.addCompany(COMPANY1);
                adminService.addCompany(COMPANY2);
                adminService.addCompany(COMPANY3);
                adminService.addCompany(COMPANY4);
                adminService.addCompany(COMPANY5);

                adminService.addCustomer(CUSTOMER1);
                adminService.addCustomer(CUSTOMER2);
                adminService.addCustomer(CUSTOMER3);
                adminService.addCustomer(CUSTOMER4);

                companyService.addCoupon(COUPON1,1);
                companyService.addCoupon(COUPON2,1);
                companyService.addCoupon(COUPON3,1);
                companyService.addCoupon(COUPON4,2);
                companyService.addCoupon(COUPON5,2);
                companyService.addCoupon(COUPON6,2);
                companyService.addCoupon(COUPON7,3);
                companyService.addCoupon(COUPON8,3);
                companyService.addCoupon(COUPON9,3);
                companyService.addCoupon(COUPON10,4);
                companyService.addCoupon(COUPON11,4);
                companyService.addCoupon(COUPON12,4);
                companyService.addCoupon(COUPON13,4);

                companyService.addCoupon(COUPON14,5);
                companyService.addCoupon(COUPON15,5);
                companyService.addCoupon(COUPON16,5);
                companyService.addCoupon(COUPON17,5);
                companyService.addCoupon(COUPON18,5);
                companyService.addCoupon(COUPON19,5);
                companyService.addCoupon(COUPON20,5);
                companyService.addCoupon(COUPON21,5);
                companyService.addCoupon(COUPON22,5);
                companyService.addCoupon(COUPON23,5);
                companyService.addCoupon(COUPON24,5);
                companyService.addCoupon(COUPON25,5);

                customerService.purchaseCoupon(COUPON1,1);
                customerService.purchaseCoupon(COUPON2,1);
                customerService.purchaseCoupon(COUPON3,1);
                customerService.purchaseCoupon(COUPON4,1);
                customerService.purchaseCoupon(COUPON5,1);
                customerService.purchaseCoupon(COUPON6,1);
                customerService.purchaseCoupon(COUPON7,1);
                customerService.purchaseCoupon(COUPON8,1);
                customerService.purchaseCoupon(COUPON9,1);
                customerService.purchaseCoupon(COUPON10,1);
                customerService.purchaseCoupon(COUPON11,1);
                customerService.purchaseCoupon(COUPON12,1);
                customerService.purchaseCoupon(COUPON13,1);
                customerService.purchaseCoupon(COUPON1,2);
                customerService.purchaseCoupon(COUPON2,2);
                customerService.purchaseCoupon(COUPON3,2);
                customerService.purchaseCoupon(COUPON1,3);
                customerService.purchaseCoupon(COUPON10,3);
            }
        };
    }
}
