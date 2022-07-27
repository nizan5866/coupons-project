package coupon.system.core.security;

import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Coupon;
import coupon.system.core.entity_beans.NewCompanyRegistration;
import coupon.system.core.entity_beans.NewCustomerRegistration;
import coupon.system.core.exception.CouponSystemException;
import coupon.system.core.exception.HttpResponseBody;
import coupon.system.core.exception.InvalidLogin;
import coupon.system.core.services.CompanyService;
import coupon.system.core.services.CustomerService;
import coupon.system.core.services.GeneralService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

//TODO maybe i can actually change back the json ignore on the coupons :)

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GeneralService generalService;

    @GetMapping("/get-all-companies-name")
    public String[] getAllCompaniesName() throws CouponSystemException {
        return generalService.getAllCompaniesName();
    }

    @GetMapping("/get-all-coupons")
    public List<Coupon> getAllCoupons(
            @RequestParam int amountGreaterThan, @RequestParam String companyName, @RequestParam String category,
            @RequestParam double maxPrice, @RequestParam String endDate)
            throws CouponSystemException {
        LocalDate endDateParsed;
        Category category1;
        String companyName1;
        try {
            endDateParsed = LocalDate.parse(endDate).minusDays(1);
        } catch (Throwable e) {
            endDateParsed = null;
        }
        try {
            category1 = Category.valueOf(category);
        } catch (Throwable e) {
            category1 = null;
        }
        if(companyName.equals("")){
            companyName1 = null;
        }else{
            companyName1 = companyName;
        }
        return generalService.getAllCoupons(amountGreaterThan, companyName1, category1, maxPrice, endDateParsed);
    }

    @PostMapping("/new-customer-registration-request")
    public boolean newCustomerRegistrationRequest(@RequestBody NewCustomerRegistration ncr) throws CouponSystemException {
        return generalService.customerRegistrationRequest(ncr);
    }

    @PostMapping("/new-company-registration-request")
    public boolean newCompanyRegistrationRequest(@RequestBody NewCompanyRegistration ncr)
            throws CouponSystemException {
        return generalService.companyRegistrationRequest(ncr);
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, @RequestParam ClientType clientType)
            throws InvalidLogin {
        return switch (clientType) {
            case ADMINISTRATOR -> caseAdmin(email, password);
            case COMPANY -> caseCompany(email, password);
            case CUSTOMER -> caseCustomer(email, password);
        };
    }

    private String caseAdmin(String email, String password) throws InvalidLogin {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            return jwtUtil.generateToken(new ClientDetails(-666, email, ClientType.ADMINISTRATOR));
        } else {
            throw new InvalidLogin("One or more of the details you entered are wrong");
        }
    }

    private String caseCompany(String email, String password) throws InvalidLogin {
        int companyId = companyService.login(email, password);
        if (companyId != 0) {
            return jwtUtil.generateToken(new ClientDetails(companyId, email, ClientType.COMPANY));
        } else {
            throw new InvalidLogin("One or more of the details you entered are wrong");
        }
    }

    private String caseCustomer(String email, String password) throws InvalidLogin {
        int customerId = customerService.login(email, password);
        if (customerId != 0) {
            return jwtUtil.generateToken(new ClientDetails(customerId, email, ClientType.CUSTOMER));
        } else {
            throw new InvalidLogin("One or more of the details you entered are wrong");
        }
    }

    @ExceptionHandler
    private ResponseEntity<HttpResponseBody> exceptionHandler(InvalidLogin e) {
        HttpResponseBody responseBody = new HttpResponseBody(HttpStatus.UNAUTHORIZED.value(),e.getMessage(), Time.now());
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    @ExceptionHandler
    private ResponseEntity<HttpResponseBody> exceptionHandler(CouponSystemException e) {
        HttpResponseBody responseBody = new HttpResponseBody(HttpStatus.NOT_FOUND.value(),e.getMessage(), Time.now());
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }


}
