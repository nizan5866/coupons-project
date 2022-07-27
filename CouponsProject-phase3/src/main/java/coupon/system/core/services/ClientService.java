package coupon.system.core.services;


import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Coupon;
import coupon.system.core.exception.CouponSystemException;
import coupon.system.core.spring_repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    NewCustomerRegistrationRepository nCustomerRegRep;
    @Autowired
    NewCompanyRegistrationRepository nCompanyRegRep;


    public String[] getAllCompaniesName() throws CouponSystemException {
        String[] allCompaniesByName = companyRepository.findAllCompanyName();
        if (allCompaniesByName.length != 0) {
            return allCompaniesByName;
        } else {
            throw new CouponSystemException("there aren't any companies");
        }
    }

//    public List<Coupon> getAllCoupons() throws CouponSystemException {
//        List<Coupon> coupons = couponRepository.findAll();
//        if (coupons.size() != 0) {
//            return coupons;
//        } else {
//            throw new CouponSystemException("there aren't any coupons");
//        }
//    }


    public List<Coupon> getAllCoupons(
            int amountGreaterThan, String companyName, Category category, double maxPrice, LocalDate endDate)
            throws CouponSystemException {
        List<Coupon> coupons;
        //i think i will do in the front end, that if expiration is not explicit
        int forTheSwitch=0;
        forTheSwitch += companyName!=null?1:0;
        forTheSwitch += category!=null?10:0;
        forTheSwitch += maxPrice!=0?100:0; //in the front end, if it wasn't chose
        forTheSwitch += endDate!=null?1000:0;
        switch (forTheSwitch){
            case 1111 -> coupons = couponRepository.
                    findAllByAmountGreaterThanAndCompanyNameAndCategoryAndPriceLessThanEqualAndEndDateIsAfter(
                            amountGreaterThan, companyName, category, maxPrice, endDate);
            case 111-> coupons = couponRepository.
                    findAllByAmountGreaterThanAndCompanyNameAndCategoryAndPriceLessThanEqual(
                            amountGreaterThan, companyName, category, maxPrice);
            case 11-> coupons = couponRepository.findAllByAmountGreaterThanAndCompanyNameAndCategory(
                    amountGreaterThan, companyName, category);
            case 1-> coupons = couponRepository.findAllByAmountGreaterThanAndCompanyName(amountGreaterThan, companyName);
            case 1001-> coupons = couponRepository.findAllByAmountGreaterThanAndCompanyNameAndEndDateIsAfter(
                    amountGreaterThan, companyName, endDate);
            case 1101-> coupons = couponRepository.
                    findAllByAmountGreaterThanAndCompanyNameAndPriceLessThanEqualAndEndDateIsAfter(
                            amountGreaterThan, companyName, maxPrice, endDate);
            case 1011-> coupons = couponRepository.
                    findAllByAmountGreaterThanAndCompanyNameAndCategoryAndEndDateIsAfter(
                            amountGreaterThan, companyName, category, endDate);
            case 101-> coupons = couponRepository.findAllByAmountGreaterThanAndCompanyNameAndPriceLessThanEqual(
                    amountGreaterThan, companyName, maxPrice);
            case 1110-> coupons = couponRepository.
                    findAllByAmountGreaterThanAndCategoryAndPriceLessThanEqualAndEndDateIsAfter(
                    amountGreaterThan, category, maxPrice, endDate);
            case 110 -> coupons = couponRepository.findAllByAmountGreaterThanAndCategoryAndPriceLessThanEqual(
                    amountGreaterThan, category, maxPrice);
            case 1010 -> coupons = couponRepository.findAllByAmountGreaterThanAndCategoryAndEndDateIsAfter(
                    amountGreaterThan, category, endDate);
            case 10 -> coupons = couponRepository.findAllByAmountGreaterThanAndCategory(amountGreaterThan, category);
            case 100 -> coupons = couponRepository.findAllByAmountGreaterThanAndPriceLessThanEqual
                    (amountGreaterThan, maxPrice);
            case 1100 -> coupons = couponRepository.findAllByAmountGreaterThanAndPriceLessThanEqualAndEndDateIsAfter(
                    amountGreaterThan, maxPrice, endDate);
            case 1000 -> coupons = couponRepository.findAllByAmountGreaterThanAndEndDateIsAfter(amountGreaterThan, endDate);
            case 0 -> coupons = couponRepository.findAllByAmountGreaterThan(amountGreaterThan);
            default -> coupons = new ArrayList<>();
        }
        if (coupons.size() != 0) {
            return coupons;
        } else {
            throw new CouponSystemException("there aren't any coupons");
        }
    }

//    public List<Coupon> getAllCouponsFiltered
//            (String companyName, Category category, LocalDate endDate,
//             double minPrice, double maxPrice) throws CouponSystemException{
//        List<Coupon> coupons;
//        LocalDate startDate = LocalDate.now().minusDays(1);
//        if(companyName == null && category == null){
//
//        }
//        couponRepository.
//                findAllByCompanyNameAndCategoryAndStartDateBeforeAndEndDateAfterAndPriceBetween(
//                        companyName,category, startDate, endDate, minPrice, maxPrice);
//        if (coupons.size() != 0) {
//            return coupons;
//        } else {
//            throw new CouponSystemException("there aren't any coupons");
//        }
//    }


}
