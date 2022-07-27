package coupon.system.core.controllers;

import coupon.system.core.entity_beans.Company;
import coupon.system.core.entity_beans.Customer;
import coupon.system.core.entity_beans.NewCompanyRegistration;
import coupon.system.core.entity_beans.NewCustomerRegistration;
import coupon.system.core.exception.CouponSystemException;
import coupon.system.core.exception.HttpResponseBody;
import coupon.system.core.services.AdminService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;


    @PostMapping("/add-company")
    private void addCompany(@RequestBody Company company, @RequestHeader String token) throws CouponSystemException {
        adminService.addCompany(company);
    }


    @PutMapping("/update-company")
    private boolean updateCompany(@RequestBody Company company, @RequestHeader String token) throws CouponSystemException {
        return adminService.updateCompany(company);
    }

    @DeleteMapping("/delete-company{companyId}")
    private boolean deleteCompany(@PathVariable int companyId, @RequestHeader String token) throws CouponSystemException {
        return adminService.deleteCompany(companyId);
    }

    @GetMapping("/get-all-companies")
    private List<Company> getAllCompanies(@RequestHeader String token) throws CouponSystemException {
        return adminService.getAllCompanies();
    }

    @GetMapping(
            path = "/get-one-company{companyId}",
            produces = {"application/json", "application/xml"}
    )
    private Company getOneCompany(@PathVariable int companyId, @RequestHeader String token) throws CouponSystemException {
        return adminService.getOneCompany(companyId);
    }

    @PostMapping("/add-one-customer")
    private void addCustomer(@RequestBody Customer customer, @RequestHeader String token) throws CouponSystemException {
        adminService.addCustomer(customer);
    }

    @PutMapping("/update-customer")
    private boolean updateCustomer(@RequestBody Customer customer, @RequestHeader String token) throws CouponSystemException {
        return adminService.updateCustomer(customer);
    }

    @DeleteMapping("/delete-customer{customerId}")
    private boolean deleteCustomer(@PathVariable int customerId, @RequestHeader String token) throws CouponSystemException {
        return adminService.deleteCustomer(customerId);
    }

    @GetMapping("/get-all-customers")
    private List<Customer> getAllCustomers(@RequestHeader String token) throws CouponSystemException {
        return adminService.getAllCustomers();
    }

    @GetMapping("/get-one-customer{customerId}")
    private Customer getOneCustomer(@PathVariable int customerId, @RequestHeader String token) throws CouponSystemException {
        return adminService.getOneCustomer(customerId);
    }

    @GetMapping("/get-all-new-company-registration-requests")
    private List<NewCompanyRegistration> getAllNewCompanyRegistrationRequests
            (@RequestHeader String token) throws CouponSystemException{
        return adminService.getAllNewCompanyRegistration();
    }

    @PostMapping("/approve-new-company-registration-request")
    private boolean approveNewCompanyRegistrationRequest
            (@RequestBody NewCompanyRegistration ncr, @RequestHeader String token) throws CouponSystemException {
        return adminService.approveCompanyRequest(ncr);
    }

    @GetMapping("/get-all-new-customer-registration-requests")
    private List<NewCustomerRegistration> getAllNewCustomerRegistrationRequests
            (@RequestHeader String token) throws CouponSystemException{
        return adminService.getAllNewCustomerRegistration();
    }

    @PostMapping("/approve-new-customer-registration-request")
    private boolean approveNewCustomerRegistrationRequest
            (@RequestBody NewCustomerRegistration ncr, @RequestHeader String token) throws CouponSystemException{
        return adminService.approveCustomerRequest(ncr);
    }

    @ExceptionHandler
    private ResponseEntity<HttpResponseBody> exceptionHandler(CouponSystemException e) {
        HttpResponseBody responseBody = new HttpResponseBody(HttpStatus.NOT_FOUND.value(),e.getMessage(), Time.now());
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }



}
