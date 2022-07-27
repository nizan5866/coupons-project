package coupon.system.core.services;

import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Company;
import coupon.system.core.entity_beans.Coupon;
import coupon.system.core.exception.CouponSystemException;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Setter
public class CompanyService extends ClientService {


    public int login(String email, String password) {
        try {
            return companyRepository.findByEmailAndPassword(email, password).getId();
        } catch (Throwable throwable) {
            return 0;
        }
    }

    /**
     * this method is used for adding a new Coupon for the Company
     *
     * @param coupon an Object from the class Coupon
     * @throws CouponSystemException <p> 1) id the end date of the coupon is before now </p>
     *                               <p>2) something went wrong with the database </p>
     */
    public void addCoupon(Coupon coupon, int companyId) throws CouponSystemException {
        try {
            Company company = new Company(companyId);
            coupon.setCompany(company);
            if (coupon.getStartDate().isAfter(coupon.getEndDate())) {
                throw new CouponSystemException("cannot have coupon with start date after end date");
            }
            if (coupon.getEndDate().isBefore(LocalDate.now())) {
                throw new CouponSystemException("Cannot add Coupon with end Date that is before Today");
            }
            if (!couponRepository.existsCouponByCompanyIdAndTitle(companyId, coupon.getTitle())) {
                couponRepository.save(coupon);
            } else {
                throw new CouponSystemException("Coupon with same title already exists in this company coupons");
            }
        } catch (Throwable e) {
            throw new CouponSystemException("Cannot add that Coupon because: " + e.getMessage());
        }
    }

    /**
     * this method is used to update an existing company coupon
     *
     * @param coupon the coupon with the id needed to change with updated information
     * @throws CouponSystemException <p>1) if the updated coupon came with different company id (guess will be deleted later)</p>
     *                               <p>2) if the coupon doesnt exist/belong to this company</p>
     *                               <p>3) if coupon with the same title already belong to this company</p>
     *                               <p>4) if the end date of the coupon is before the start date of the coupon</p>
     *                               <p>5) if something went wrong with the database</p>
     */
    public void updateCoupon(Coupon coupon, int companyId) throws CouponSystemException {
        try {
            if (!couponRepository.existsCouponByCompanyIdAndId(companyId, coupon.getId())) {
                throw new CouponSystemException("there isn't coupon with id " +
                        "[" + coupon.getId() + "] that belongs to this company[" + companyId + "]");
            }
            if (!coupon.getTitle().equals(couponRepository.getById(coupon.getId()).getTitle()) &&
                    (couponRepository.existsCouponByCompanyIdAndTitle(companyId, coupon.getTitle()))) {
                throw new CouponSystemException("There is another Coupon with that same title " +
                        "that already exists in this company");
            }
            if (coupon.getStartDate().isAfter(coupon.getEndDate())) {
                throw new CouponSystemException("cannot have coupon with start date after end date");
            }
            Company company = new Company(companyId);
            coupon.setCompany(company);
            couponRepository.save(coupon);
        } catch (Throwable e) {
            throw new CouponSystemException("Update Coupon has failed because: " + e.getMessage());
        }
    }


    /**
     * this method is used for deleting a companies coupon. it is also deletes it from
     * the purchases
     *
     * @param couponID the relevant Coupon id needed to be deleted
     * @throws CouponSystemException <p>1) if there isn't a coupon with that id that belongs to this company</p>
     *                               <p>2)if something went wrong with the database</p>
     */
    public void deleteCoupon(int couponID, int companyId) throws CouponSystemException {
        try {
            if (!couponRepository.existsCouponByCompanyIdAndId(companyId, couponID)) {
                throw new CouponSystemException("there isn't coupon with id " +
                        "[" + couponID + "] that belongs to this company[" + companyId + "]");
            }
            couponRepository.deleteById(couponID);
        } catch (Throwable e) {
            throw new CouponSystemException("delete coupon with id [" + couponID + "] has failed because: "
                    + e.getMessage());
        }
    }

    /**
     * this method is used for getting all the company coupons from the database
     *
     * @return a sorted ArrayList by the coupons name from the database
     * @throws CouponSystemException if there is a problem with the database
     */
    public List<Coupon> getAllCoupons(int companyId) throws CouponSystemException {
        try {
            List<Coupon> coupons = couponRepository.findAllByCompanyId(companyId);
            if (coupons.size() == 0) {
                throw new CouponSystemException("there's no any coupon belong to this company");
            }
            return coupons;
        } catch (Throwable e) {
            throw new CouponSystemException("Getting all coupons failed because: " + e.getMessage());
        }
    }

    /**
     * this method use is to get all of the companies coupons from a certain category
     *
     * @param category the category of the coupons needed to extract
     * @return a sorted list of coupons by their Id
     * @throws CouponSystemException <p>1)if there isn't any coupons of that category that belongs to this company</p>
     *                               <p>2)if something goes wrong in the database</p>
     */
    public List<Coupon> getAllCouponsByCategory(Category category, int companyId) throws CouponSystemException {
        try {
            List<Coupon> coupons = couponRepository.findAllByCompanyIdAndCategory(companyId, category);
            if (coupons.size() == 0) {
                throw new CouponSystemException("there isn't any coupons of that category" +
                        " that belongs to this company");
            }
            return coupons;
        } catch (Throwable e) {
            throw new CouponSystemException
                    ("Getting all coupons of category" + category + "failed because :" + e.getMessage());
        }
    }

    /**
     * this method is for getting all of the company coupons up to a max price
     *
     * @param maxPrice the maximum price of the coupons wanted
     * @return the list of the coupons sorted by the price
     * @throws CouponSystemException <p>1)if there isn't any coupons belong to this company under the maximum price</p>
     *                               <p>2)if something goes wrong in the database</p>
     */
    public List<Coupon> getAllCouponsByMaxPrice(double maxPrice, int companyId) throws CouponSystemException {
        try {
            List<Coupon> coupons = couponRepository.findAllByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);
            if (coupons.size() == 0) {
                throw new CouponSystemException("there isn't any coupons lower then the price: " +
                        maxPrice + " that belongs to this company");
            }
            return coupons;
        } catch (Throwable e) {
            throw new CouponSystemException("Getting all coupons by max price failed because : " + e.getMessage());
        }
    }

    /**
     * this method is used for getting all of the company details
     *
     * @return an Object of the class Company
     * @throws CouponSystemException <p>if something goes wrong in the database</p>
     */
    public Company getCompanyDetails(int companyId) throws CouponSystemException {
        try {
            return companyRepository.findById(companyId).get();
        } catch (Throwable e) {
            throw new CouponSystemException("Getting Company details failed because : " + e.getMessage());

        }
    }


}
