package coupon.system.core.controllers;

import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Company;
import coupon.system.core.entity_beans.Coupon;
import coupon.system.core.exception.CouponSystemException;
import coupon.system.core.services.CompanyService;
import coupon.system.core.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/company")
public class CompanyController{

    @Autowired
    CompanyService companyService;
    @Autowired
    JwtUtil jwtUtil;


    @PostMapping("/add-coupon")
    private void addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) throws CouponSystemException {
        companyService.addCoupon(coupon, jwtUtil.extractId(token));
    }

    @PutMapping("/update-coupon")
    private void updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token) throws CouponSystemException {
        companyService.updateCoupon(coupon, jwtUtil.extractId(token));
    }

    @DeleteMapping("/delete-coupon{couponId}")
    private void deleteCoupon(@PathVariable int couponId, @RequestHeader String token) throws CouponSystemException {
        companyService.deleteCoupon(couponId, jwtUtil.extractId(token));
    }

    @GetMapping("/get-all-coupons")
    private List<Coupon> getAllCoupons(@RequestHeader String token) throws CouponSystemException {
        return companyService.getAllCoupons(jwtUtil.extractId(token));
    }

    @GetMapping("/get-all-coupons-by-category")
    private List<Coupon> getAllCouponsByCategory(@RequestParam Category category, @RequestHeader String token) throws CouponSystemException {
        return companyService.getAllCouponsByCategory(category, jwtUtil.extractId(token));
    }

    @GetMapping("/get-all-coupons-by-max-price")
    private List<Coupon> getAllCouponsByMaxPrice(@RequestParam double maxPrice, @RequestHeader String token) throws CouponSystemException {
        return companyService.getAllCouponsByMaxPrice(maxPrice, jwtUtil.extractId(token));
    }

    @GetMapping("/get-company-details")
    public Company getCompanyDetails(@RequestHeader String token) throws CouponSystemException {
        return companyService.getCompanyDetails(jwtUtil.extractId(token));
    }

    @ExceptionHandler
    private String exceptionHandler(CouponSystemException e){
        return e.getMessage();
    }

}
