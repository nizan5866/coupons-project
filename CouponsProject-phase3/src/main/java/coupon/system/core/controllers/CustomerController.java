package coupon.system.core.controllers;

import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Coupon;
import coupon.system.core.entity_beans.Customer;
import coupon.system.core.exception.CouponSystemException;
import coupon.system.core.exception.HttpResponseBody;
import coupon.system.core.services.CustomerService;
import coupon.system.core.security.JwtUtil;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController{

    @Autowired
    CustomerService customerService;
    @Autowired
    JwtUtil jwtUtil;


    @PutMapping("/purchase-coupon")
    public boolean purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader String token) throws CouponSystemException {
        return customerService.purchaseCoupon(coupon, jwtUtil.extractId(token));
    }

    @GetMapping("/get-all-coupons")
    public List<Coupon> getAllCoupons(@RequestHeader String token) throws CouponSystemException {
        return customerService.getAllCoupons(jwtUtil.extractId(token));
    }

    @GetMapping("/get-all-coupons-by-category")
    public List<Coupon> getAllCouponsByCategory(@RequestParam Category category, @RequestHeader String token) throws CouponSystemException {
        return customerService.getAllCouponsByCategory(category, jwtUtil.extractId(token));
    }

    @GetMapping("get-all-coupons-by-max-price")
    public List<Coupon> getAllCouponsByMaxPrice(@RequestParam double maxPrice, @RequestHeader String token) throws CouponSystemException {
        return customerService.getAllCouponsByMaxPrice(maxPrice, jwtUtil.extractId(token));
    }

    @GetMapping("get-customer-details")
    public Customer getCustomerDetails(@RequestHeader String token) throws CouponSystemException {
        return customerService.getCustomerDetails(jwtUtil.extractId(token));
    }

    @ExceptionHandler
    private ResponseEntity<HttpResponseBody> exceptionHandler(CouponSystemException e){
        HttpResponseBody responseBody = new HttpResponseBody(HttpStatus.NOT_FOUND.value(),e.getMessage(), Time.now());
//        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

//    private ResponseEntity<HttpResponseBody> exceptionHandler(CouponSystemException e) {
//        HttpResponseBody responseBody = new HttpResponseBody(HttpStatus.NOT_FOUND.value(),e.getMessage(), Time.now());
//        System.out.println(e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
//    }

}
