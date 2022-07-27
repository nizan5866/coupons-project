//package coupon.system.core.test;
//
//import coupon.system.core.entity_beans.Category;
//import coupon.system.core.entity_beans.Company;
//import coupon.system.core.entity_beans.Coupon;
//import coupon.system.core.entity_beans.Customer;
//import coupon.system.core.exception.CouponSystemException;
//import coupon.system.core.services.AdminFacade;
//import coupon.system.core.services.CompanyFacade;
//import coupon.system.core.services.CustomerFacade;
//import coupon.system.core.security.ClientType;
//import coupon.system.core.login_manager.LoginManager;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class Tests {
//
//    //=================================================================================================================
//    private static final String RESET = "\u001B[0m";
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String YELLOW = "\u001B[33m";
//    //=================================================================================================================
//    public static final Company COMPANY1 = new Company(1, "Apple", "apple@gmail.com", "1234");
//    public static final Company COMPANY2 = new Company(2, "Google", "google@gmail.com", "1234");
//    public static final Company COMPANY3 = new Company(3, "Amazon", "amazon@gmail.com", "1234");
//    public static final Company COMPANY4 = new Company(4, "Microsoft", "microsoft@gmail.com", "1234");
//
//    //=================================================================================================================
//
//    public static final Coupon COUPON1 = new Coupon(1, COMPANY1, Category.SMARTPHONES, "20% off smartphones", "get 20% discount on smartphones", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 100, "@@@");
//    public static final Coupon COUPON2 = new Coupon(2, COMPANY1, Category.COMPUTERS, "20% off computers", "get 20% discount on computers", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 200, "@@@");
//    public static final Coupon COUPON3 = new Coupon(3, COMPANY1, Category.CLOUD_STORAGE, "50Gb of cloud storage", "get cloud Storage", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 300, "@@@");
//
//    public static final Coupon COUPON4 = new Coupon(4, COMPANY2, Category.SMARTPHONES, "20% off smartphones", "get 20% discount on smartphones", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 300, "@@@");
//    public static final Coupon COUPON5 = new Coupon(5, COMPANY2, Category.COMPUTERS, "20% off computers", "get 20% discount on computers", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 100, "@@@");
//    public static final Coupon COUPON6 = new Coupon(6, COMPANY2, Category.CLOUD_STORAGE, "5Gb cloudStorage", "get cloud Storage", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 200, "@@@");
//
//    public static final Coupon COUPON7 = new Coupon(7, COMPANY3, Category.CLOUD_STORAGE, "20% off cloud space", "get 20% discount on cloud space", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 1, 50, "@@@");
//    public static final Coupon COUPON8 = new Coupon(8, COMPANY3, Category.SOFTWARE, "amazon prime", "one month of amazing prime", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 30, "@@@");
//    public static final Coupon COUPON9 = new Coupon(9, COMPANY3, Category.SOFTWARE, "50$ gift card", "get 50$ gift card for amazon", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 45, "@@@");
//
//    public static final Coupon COUPON10 = new Coupon(10, COMPANY4, Category.SOFTWARE, "20% off office", "get 20% discount on office", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 149.9, "@@@");
//    public static final Coupon COUPON11 = new Coupon(11, COMPANY4, Category.SOFTWARE, "ONLY EXCEL", "get excel desktop program", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 49.9, "@@@");
//    public static final Coupon COUPON12 = new Coupon(12, COMPANY4, Category.COMPUTERS, "20% off computers", "get 20% discount on computers", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 79.9, "@@@");
//    public static final Coupon COUPON13 = new Coupon(13, COMPANY4, Category.SOFTWARE, "windows for students", "get windows for students at a major discount", LocalDate.parse("2021-01-01"), LocalDate.parse("2022-12-31"), 5, 300.99, "@@@");
//
//    //=================================================================================================================
//
//    public static final Customer CUSTOMER1 = new Customer(1, "Avraham", "Cohen", "avrahamcohen@gmail.com", "1234");
//    public static final Customer CUSTOMER2 = new Customer(2, "Beny", "Gabbay", "benygabbay@gmail.com", "1234");
//    public static final Customer CUSTOMER3 = new Customer(3, "Dana", "Cohen", "danacohen@gmail.com", "1234");
//    public static final Customer CUSTOMER4 = new Customer(4, "Fiona", "Floochi", "fionafloochi@gmail.com", "1234");
//
//    //=================================================================================================================
//
//    public static void success() {
//        System.out.println(GREEN + "SUCCESS" + RESET);
//    }
//
//    public static void success(Throwable e) {
//        System.out.println(GREEN + "SUCCESS: " + e.getMessage() + RESET);
//    }
//
//    public static void fail(Throwable e) {
//        System.out.println(RED + "FAILED: " + e.getMessage() + RESET);
//    }
//
//    public static void fail() {
//        System.out.println(RED + "FAILED" + RESET);
//    }
//
//    //=================================================================================================================
//
//    public static void testForCustomerFacade(LoginManager loginManager) throws CouponSystemException {
//        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><>");
//        System.out.println("<><><><><> TESTING CUSTOMER FACADE  <><><><><>");
//        System.out.println("<><><><><><><><><><><><><><><><>><><><><><><><>\n");
//        System.out.print("test login method : ");
//        CustomerFacade avraham = (CustomerFacade) loginManager.login
//                (CUSTOMER1.getEmail(), CUSTOMER1.getPassword(), ClientType.CUSTOMER);
//        CustomerFacade beny = (CustomerFacade) loginManager.login
//                (CUSTOMER2.getEmail(), CUSTOMER2.getPassword(), ClientType.CUSTOMER);
//        CustomerFacade dana = (CustomerFacade) loginManager.login
//                (CUSTOMER3.getEmail(), CUSTOMER3.getPassword(), ClientType.CUSTOMER);
//        CustomerFacade fiona = (CustomerFacade) loginManager.login
//                (CUSTOMER4.getEmail(), CUSTOMER4.getPassword(), ClientType.CUSTOMER);
//        success();
//
//        AdminFacade aF = (AdminFacade) loginManager.login
//                ("admin@admin", "admin", ClientType.ADMINISTRATOR);
//        Company company = new Company(4);
//        CompanyFacade companyFacade = (CompanyFacade) loginManager.login
//                (COMPANY4.getEmail(), COMPANY4.getPassword(), ClientType.COMPANY);
//        Coupon coupon = new Coupon(15, company, Category.SMARTPHONES, "Fake Coupon for 4", "fakefakefake",
//                LocalDate.now().plusDays(1), LocalDate.now().plusDays(20), 10, 10, "@@@");
//        companyFacade.addCoupon(coupon);
//
//        purchaseCouponMethodTest(avraham, beny, dana);
//        getAllCouponsMethodTest(avraham, fiona);
//        getAllCouponsByCategoryMethodTest(avraham,beny);
//        getAllCouponsByMaxPriceMethodTest(avraham);
//        getCustomerDetailsMethodTest(avraham);
//
//    }
//
//    public static void getCustomerDetailsMethodTest(CustomerFacade cF1){
//        System.out.println("------------get Customer details method--------------");
//        try{
//            Customer customer = cF1.getCustomerDetails();
//            System.out.println(customer);
//            System.out.println(customer.getCoupons());
//            success();
//        }catch (Throwable e){
//            fail(e);
//        }
//
//    }
//
//    public static void getAllCouponsByMaxPriceMethodTest(CustomerFacade cF1){
//        System.out.println("------------get all coupons up to max price METHOD--------------");
//
//        {// legit
//            System.out.print("test 1: (legit, 8 results) :");
//            try{
//                if(cF1.getAllCouponsByMaxPrice(150).size() == 8){
//                    success();
//                }else {
//                    fail();
//                }
//            }catch (Throwable e){
//                fail(e);
//            }
//        }
//
//        {// test 2 zero results
//            System.out.print("test 2 : (zero results) :");
//            try{
//                cF1.getAllCouponsByMaxPrice(2);
//                fail();
//            }catch (Throwable e){
//                success(e);
//            }
//
//        }
//
//    }
//
//    public static void getAllCouponsByCategoryMethodTest(CustomerFacade cF1, CustomerFacade cF2) {
//        System.out.println("------------get all coupons of category METHOD--------------");
//        {//test 1 legit 5
//            try {
//                System.out.print("test 1 : (legit, 5) :");
//                if (cF1.getAllCouponsByCategory(Category.SOFTWARE).size() == 5) {
//                    success();
//                } else {
//                    fail();
//                }
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {// test 0 from software
//            System.out.print("test 2 : (0 results) :");
//            try{
//                cF2.getAllCouponsByCategory(Category.CLOUD_STORAGE);
//                fail();
//            }catch (Throwable t){
//                success(t);
//            }
//
//        }
//    }
//
//    public static void getAllCouponsMethodTest(CustomerFacade cF1, CustomerFacade cF2) {
//        System.out.println("------------get all coupons METHOD--------------");
//        {//test 1 legit
//            try {
//                System.out.print("test 1 : (legit, 13 coupons) : ");
//                List<Coupon> coupons = cF1.getAllCoupons();
//                if (coupons.size() == 13) {
//                    success();
//                } else {
//                    fail();
//                }
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {// test 2 0 coupons
//            try {
//                System.out.print("test 2: (0 coupons) : ");
//                List<Coupon> coupons = cF2.getAllCoupons();
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//
//        }
//
//    }
//
//    public static void purchaseCouponMethodTest(CustomerFacade cF1, CustomerFacade cF2, CustomerFacade cF3) {
//        System.out.println("------------testing purchase coupon METHOD--------------");
//        {//legit
//            try {
//                System.out.print("test 1: (legit) : ");
//                cF1.purchaseCoupon(COUPON1);
//                cF1.purchaseCoupon(COUPON2);
//                cF1.purchaseCoupon(COUPON3);
//                cF1.purchaseCoupon(COUPON4);
//                cF1.purchaseCoupon(COUPON5);
//                cF1.purchaseCoupon(COUPON6);
//                cF1.purchaseCoupon(COUPON7);
//                cF1.purchaseCoupon(COUPON8);
//                cF1.purchaseCoupon(COUPON9);
//                cF1.purchaseCoupon(COUPON10);
//                cF1.purchaseCoupon(COUPON11);
//                cF1.purchaseCoupon(COUPON12);
//                cF1.purchaseCoupon(COUPON13);
//                cF2.purchaseCoupon(COUPON4);
//                cF2.purchaseCoupon(COUPON11);
//                cF2.purchaseCoupon(COUPON12);
//                cF2.purchaseCoupon(COUPON13);
//                cF3.purchaseCoupon(COUPON10);
//                success();
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 - purchasing coupon with no amount
//            System.out.print("test 2: (purchase coupon with amount 0) :");
//            try {
//                cF3.purchaseCoupon(COUPON7);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//
//        {//test 3 - trying to buy coupon before it's available
//            System.out.print("test 3 : (trying to buy coupon before start date has yet to come) : ");
//            try {
//                Coupon coupon = new Coupon(15);
//                cF3.purchaseCoupon(coupon);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//
//        }
//
//        {//test 4 - trying to buy coupon before it's available
//            System.out.print("test 4 : (trying to buy coupon that was already purchased) : ");
//            try {
//                cF1.purchaseCoupon(COUPON1);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//
//        }
//
//    }
//
//
//    //=================================================================================================================
//    public static void testForCompanyFacade(LoginManager loginManager) throws CouponSystemException {
//        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><>");
//        System.out.println("<><><><><> TESTING COMPANY FACADE  <><><><><>");
//        System.out.println("<><><><><><><><><><><><><><><><>><><><><><><>\n");
//
//        System.out.print("test login method :");
//        CompanyFacade apple = (CompanyFacade) loginManager.login
//                (COMPANY1.getEmail(), COMPANY1.getPassword(), ClientType.COMPANY);
//        CompanyFacade google = (CompanyFacade) loginManager.login
//                (COMPANY2.getEmail(), COMPANY2.getPassword(), ClientType.COMPANY);
//        CompanyFacade amazon = (CompanyFacade) loginManager.login
//                (COMPANY3.getEmail(), COMPANY3.getPassword(), ClientType.COMPANY);
//        CompanyFacade microsoft = (CompanyFacade) loginManager.login
//                (COMPANY4.getEmail(), COMPANY4.getPassword(), ClientType.COMPANY);
//        success();
//
//        addCouponMethodTest(apple, google, amazon, microsoft);
//        updateCouponMethodTest(apple);
//        deleteCouponMethodTest(microsoft);
//        getAllCouponsMethodTest(apple);
//        getAllCouponsByCategoryMethodTest(microsoft);
//        getAllCouponsByMaxPriceMethodTest(apple);
//        getCompanyDetailsMethodTest(apple);
//
//    }
//
//    public static void getCompanyDetailsMethodTest(CompanyFacade cF1) {
//        System.out.println("------------testing get Company details METHOD--------------");
//        try {
//            Company company = cF1.getCompanyDetails();
//            System.out.println(company);
//            System.out.println(company.getCoupons());
//            success();
//        } catch (Throwable e) {
//            fail(e);
//        }
//    }
//
//    public static void getAllCouponsByMaxPriceMethodTest(CompanyFacade cF1) {
//        System.out.println("-----------testing get all coupons by max price METHOD--------------");
//        {//test 1 legit 2
//            try {
//                System.out.print("test 1: (legit, 2 results) : ");
//                List<Coupon> coupons = cF1.getAllCouponsByMaxPrice(200);
//                System.out.println(coupons);
//                if (coupons.size() == 2) {
//                    success();
//                } else {
//                    fail();
//                }
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 0 results
//            try {
//                System.out.print("test 2: (0 results) : ");
//                List<Coupon> coupons = cF1.getAllCouponsByMaxPrice(50);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//
//
//    }
//
//    public static void getAllCouponsByCategoryMethodTest(CompanyFacade cF4) {
//        System.out.println("------------testing get all coupons by category METHOD--------------");
//        {//test 1 legit 2
//            System.out.print("test 1: (legit, 2 coupons) :");
//            try {
//                List<Coupon> coupons = cF4.getAllCouponsByCategory(Category.SOFTWARE);
//                if (coupons.size() == 3) {
//                    success();
//                } else {
//                    fail();
//                }
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {// test 2 zero coupons
//            System.out.print("test 2: (legit, 0 coupons) :");
//            try {
//                List<Coupon> coupons = cF4.getAllCouponsByCategory(Category.SMARTPHONES);
//                if (coupons.size() == 0) {
//                    success();
//                } else {
//                    fail();
//                }
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//    }
//
//    public static void getAllCouponsMethodTest(CompanyFacade cF1) {
//        System.out.println("------------------testing get all coupons METHOD--------------------");
//        try {
//            List<Coupon> coupons = cF1.getAllCoupons();
//            System.out.println(coupons);
//            if (coupons.size() == 3) {
//                success();
//            } else {
//                fail();
//            }
//        } catch (Throwable e) {
//            fail(e);
//        }
//    }
//
//    public static void deleteCouponMethodTest(CompanyFacade cF4) {
//        System.out.println("--------------------testing delete coupon METHOD--------------------");
//        {// test 1 legit
//            Coupon coupon = new Coupon(14, COUPON1.getCompany(), COUPON1.getCategory(), "FakeTitle",
//                    COUPON1.getDescription(), COUPON1.getStartDate(), COUPON1.getEndDate(), COUPON1.getAmount(),
//                    COUPON1.getPrice(), COUPON1.getImage());
//            System.out.print("test 1 : (legit) :");
//            try {
//                cF4.addCoupon(coupon);
//                cF4.deleteCoupon(14);
//                success();
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 delete coupon that doesnt belong to this company
//            System.out.print("test 2: (deleting a coupon that doesn't belong to this company) : ");
//            try {
//                cF4.deleteCoupon(1);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//
//    }
//
//    public static void updateCouponMethodTest(CompanyFacade cF1) {
//        System.out.println("--------------------testing update coupon METHOD--------------------");
//
//        {//test 1 legit
//            System.out.print("test 1: (legit) :");
//            Coupon coupon = new Coupon(COUPON1.getId(), COUPON1.getCompany(), COUPON1.getCategory(), COUPON1.getTitle(),
//                    COUPON1.getDescription(), COUPON1.getStartDate(), COUPON1.getEndDate(), 100,
//                    COUPON1.getPrice(), COUPON1.getImage());
//            try {
//                cF1.updateCoupon(coupon);
//                success();
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 not legit
//            System.out.print("test 2: (update coupon to title already exists) :");
//            Coupon coupon = new Coupon(COUPON2.getId(), COUPON1.getCompany(), COUPON1.getCategory(), COUPON1.getTitle(),
//                    COUPON1.getDescription(), COUPON1.getStartDate(), COUPON1.getEndDate(), 100,
//                    COUPON1.getPrice(), COUPON1.getImage());
//            try {
//                cF1.updateCoupon(coupon);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//
//        {//test 3 trying to update coupon that doesnt belong to this company
//            System.out.print("test 3: (update coupon that doesnt belong to this company) :");
//            Coupon coupon = new Coupon(COUPON10.getId(), COUPON1.getCompany(), COUPON1.getCategory(), COUPON1.getTitle(),
//                    COUPON1.getDescription(), COUPON1.getStartDate(), COUPON1.getEndDate(), 100,
//                    COUPON1.getPrice(), COUPON1.getImage());
//            try {
//                cF1.updateCoupon(coupon);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//
//        }
//
//    }
//
//    public static void addCouponMethodTest(CompanyFacade cF1, CompanyFacade cF2, CompanyFacade cF3, CompanyFacade cF4) {
//        System.out.println("--------------------testing add coupon METHOD--------------------");
//
//        {// test 1 legit
//            System.out.print("test 1: (legit) : ");
//            try {
//                cF1.addCoupon(COUPON1);
//                cF1.addCoupon(COUPON2);
//                cF1.addCoupon(COUPON3);
//                cF2.addCoupon(COUPON4);
//                cF2.addCoupon(COUPON5);
//                cF2.addCoupon(COUPON6);
//                cF3.addCoupon(COUPON7);
//                cF3.addCoupon(COUPON8);
//                cF3.addCoupon(COUPON9);
//                cF4.addCoupon(COUPON10);
//                cF4.addCoupon(COUPON11);
//                cF4.addCoupon(COUPON12);
//                cF4.addCoupon(COUPON13);
//                success();
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 not legit
//            System.out.print("test 2: (coupon with same title to same company) : ");
//            try {
//                cF1.addCoupon(COUPON1);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//    }
//
//
//    //=================================================================================================================
//
//
//    public static void testForAdminFacade(LoginManager loginManager) throws CouponSystemException {
//
//        System.out.println("\n<><><><><><><><><><><><><><><><>><><><><><>");
//        System.out.println("<><><><><> TESTING ADMIN FACADE  <><><><><>");
//        System.out.println("<><><><><><><><><><><><><><><><>><><><><><>\n");
//
//        System.out.print("test login method: ");
//        AdminFacade aF = (AdminFacade) loginManager.login
//                ("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//        success();
//
//        addCompanyMethodTest(aF);
//        updateCompanyMethodTest(aF);
//        deleteCompanyMethodTest(aF);
//        getOneCompanyMethodTest(aF);
//        getAllCompaniesMethodTest(aF);
//        addCustomerMethodTest(aF);
//        updateCustomerMethodTest(aF);
//        deleteCustomerMethodTest(aF);
//        getOneCustomerMethodTest(aF);
//        getAllCustomersMethodTest(aF);
//
//    }
//
//    public static void getAllCompaniesMethodTest(AdminFacade aF) {
//        System.out.println("--------------------testing get all companies METHOD--------------------");
//        try {
//            System.out.println(aF.getAllCompanies());
//            success();
//        } catch (Throwable e) {
//            fail(e);
//        }
//    }
//
//    public static void getAllCustomersMethodTest(AdminFacade aF) {
//        System.out.println("--------------------testing get all Customer METHOD--------------------");
//        try {
//            System.out.println(aF.getAllCustomers());
//            success();
//        } catch (Throwable e) {
//            fail(e);
//        }
//    }
//
//    public static void getOneCustomerMethodTest(AdminFacade aF) {
//        System.out.println("--------------------testing get one Customer METHOD--------------------");
//
//        {//test 1 legit
//            System.out.print("test 1: (legit) :");
//            try {
//                aF.getOneCustomer(1);
//                success();
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 not a valid customer
//            System.out.print("test 2 : (customer that doesnt exist) :");
//            try {
//                aF.getOneCustomer(10);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//
//        }
//    }
//
//    public static void deleteCustomerMethodTest(AdminFacade aF) {
//        System.out.println("--------------------testing delete Customer METHOD--------------------");
//
//        {//test 1 legit
//            System.out.print("test 1: (legit) :");
//            try {
//                Customer customer = new Customer(5, "FakeCustomer", "fakelastname", "fakeemail2@gmail.com", "1234");
//                aF.addCustomer(customer);
//                aF.deleteCustomer(5);
//                success();
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 not legit
//            System.out.print("test 2: (delete customer that doesnt exist[id 10]) :");
//            try {
//                aF.deleteCustomer(10);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//
//
//    }
//
//    public static void updateCustomerMethodTest(AdminFacade aF) {
//        System.out.println("--------------------testing update Customer METHOD--------------------");
//
//        {//test 1 update legit
//            System.out.print("test 1: (update legit[change email])");
//            Customer customer = new Customer(CUSTOMER1.getId(), CUSTOMER1.getFirstName(), CUSTOMER1.getLastName(), CUSTOMER1.getEmail(), CUSTOMER1.getPassword());
//            customer.setEmail("FakeEmail@gmail.com");
//            try {
//                aF.updateCustomer(customer);
//                System.out.print(aF.getOneCustomer(1) + " ");
//                success();
//                Customer customer1 = new Customer(1, CUSTOMER1.getFirstName(), CUSTOMER1.getLastName(), "avrahamcohen@gmail.com", "1234");
//                aF.updateCustomer(customer1);
//            } catch (Throwable e) {
//                fail(e);
//            }
//        }
//
//        {//test not legit
//            System.out.print("test 2: (updating customer with id 10 that doesnt exist) :");
//            Customer customer = new Customer(10, "Avraham", "Cohen", "dkjfnsdkfh@gmail.com", "1234");
//            try {
//                aF.updateCustomer(customer);
//                fail();
//            } catch (Throwable throwable) {
//                success(throwable);
//            }
//
//        }
//
//        {//test trying to update customer to some else email
//            System.out.print("test 3: (updating customer to email that other customer already have) :");
//            try {
//                Customer customer = new Customer
//                        (CUSTOMER2.getId(), CUSTOMER2.getFirstName(), CUSTOMER2.getLastName(), CUSTOMER2.getEmail(), CUSTOMER2.getPassword());
//                customer.setEmail(CUSTOMER1.getEmail());
//                aF.updateCustomer(customer);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//
//        }
//
//    }
//
//    public static void addCustomerMethodTest(AdminFacade aF) {
//        System.out.println("--------------------testing add Customer METHOD--------------------");
//        {//test 1 add legit
//            System.out.print("test 1: (legit) :");
//            try {
//                aF.addCustomer(CUSTOMER1);
//                aF.addCustomer(CUSTOMER2);
//                aF.addCustomer(CUSTOMER3);
//                aF.addCustomer(CUSTOMER4);
//                success();
//            } catch (CouponSystemException e) {
//                fail(e);
//            }
//        }
//
//        {//test 2 add not legit
//            System.out.print("test 2: (add customer with same Email) :");
//            try {
//                Customer customer = new Customer(5, "FakeCustomer", "FakeLastName", CUSTOMER1.getEmail(), "1234");
//                aF.addCustomer(customer);
//                fail();
//            } catch (Throwable e) {
//                success(e);
//            }
//        }
//
//    }
//
//    public static void getOneCompanyMethodTest(AdminFacade aF) {
//        //test get one company METHOD
//        System.out.println("--------------------testing get one company METHOD--------------------");
//        {// test legit
//            System.out.print("test 1: (legit) :");
//            try {
//                Company company = aF.getOneCompany(1);
//                if (company != null) {
//                    success();
//                } else {
//                    fail();
//                }
//            } catch (CouponSystemException e) {
//                fail(e);
//            }
//
//        }
//
//        {//test get one company not legit
//            System.out.print("test 2: (not exist) :");
//            try {
//                Company company = aF.getOneCompany(10);
//                fail();
//            } catch (CouponSystemException e) {
//                success(e);
//            }
//
//        }
//
//
//    }
//
//
//    public static void addCompanyMethodTest(AdminFacade aF) {
//        { // add the companies method tests
//            System.out.println("--------------------testing add new company METHOD--------------------");
//            {//test one, add legit
//                System.out.print("test 1: (adding 4 legit companies) :");
//                try {
//                    aF.addCompany(COMPANY1);
//                    aF.addCompany(COMPANY2);
//                    aF.addCompany(COMPANY3);
//                    aF.addCompany(COMPANY4);
//                    success();
//                } catch (CouponSystemException e) {
//                    fail(e);
//                }
//            }
//
//            {//test 2- add company with same name and mail
//                System.out.print("test 2: (adding company with same name and email) :");
//                try {
//                    aF.addCompany(COMPANY1);
//                    fail();
//                } catch (CouponSystemException e) {
//                    success(e);
//                }
//            }
//
//            {//test 3 - add company with the same email
//                System.out.print("test 3: (adding company with the same email) :");
//                Company company = new Company(1, "mapple", "apple@gmail.com", "1234");
//                try {
//                    aF.addCompany(company);
//                    fail();
//                } catch (CouponSystemException e) {
//                    success(e);
//                }
//            }
//
//            {//test 4 - add company with the same name
//                System.out.print("test 4: (adding company with the same name) :");
//                Company company = new Company(1, "Apple", "apple123@gmail.com", "1234");
//                try {
//                    aF.addCompany(company);
//                    fail();
//                } catch (CouponSystemException e) {
//                    success(e);
//                }
//            }
//        }
//    }
//
//    public static void updateCompanyMethodTest(AdminFacade aF) {
//        {//test the update company method
//            System.out.println("--------------------testing update company METHOD--------------------");
//            {//update company legit
//                System.out.print("test 1: (update legit) :");
//                try {
//                    Company company = new Company(1, "Apple", "apple123@gmail.com", "1111");
//                    aF.updateCompany(company);
//                    success();
//                    aF.updateCompany(COMPANY1);
//                } catch (CouponSystemException e) {
//                    fail(e);
//                }
//            }
//
//            {//update name not legit
//                System.out.print("test 2: (update name not legit) :");
//                try {
//                    Company company = new Company(1, "FakeCompany", "apple@gmail.com", "1234");
//                    aF.updateCompany(company);
//                    fail();
//                } catch (CouponSystemException e) {
//                    success(e);
//                }
//            }
//
//            {//update with no valid Id
//                System.out.print("test 3: (update company that doesnt exist by id) :");
//                try {
//                    Company company = new Company(10);
//                    aF.updateCompany(company);
//                    fail();
//                } catch (CouponSystemException e) {
//                    success(e);
//                }
//            }
//        }
//    }
//
//    public static void deleteCompanyMethodTest(AdminFacade aF) {
//        {//test delete company method
//            System.out.println("--------------------testing delete company METHOD--------------------");
//            {// delete legit
//                System.out.print("test 1: (delete legit) :");
//                Company company = new Company(5, "FakeCompany", "fakecompany@gmail.com", "1234");
//                try {
//                    aF.addCompany(company);
//                    aF.deleteCompany(5);
//                    success();
//                } catch (CouponSystemException e) {
//                    fail(e);
//                }
//            }
//
//            {// delete company that doesnt exist
//                System.out.print("test 2: (deleting a company that doesnt exist) :");
//                try {
//                    aF.deleteCompany(5);
//                    fail();
//                } catch (Throwable e) {
//                    success(e);
//                }
//            }
//        }
//    }
//
//
//}
