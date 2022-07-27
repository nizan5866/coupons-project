package coupon.system.core.services;

import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Coupon;
import coupon.system.core.entity_beans.Customer;
import coupon.system.core.exception.CouponSystemException;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Setter
public class CustomerService extends ClientService {


    public int login(String email, String password) {
        try {
            return customerRepository.findByEmailAndPassword(email, password).getId();
        } catch (Throwable e) {
            return 0;
        }
    }

    /**
     * this method is used for a safe purchasing of a coupon for a customer
     *
     * @param coupon an Object from the class Coupon
     * @throws CouponSystemException <p>1) if the coupon does not exist</p>
     *                               <p>2)if the coupon is out of stock</p>
     *                               <p>3)if the coupon is not yet available for purchase</p>
     *                               <p>4)if the coupon has passed it's expiration date</p>
     *                               <p>if the coupon was already purchased by that client</p>
     */
    public boolean purchaseCoupon(Coupon coupon, int customerId) throws CouponSystemException {
        try {
            int couponID = coupon.getId();
            isCouponPurchasable(couponID);
            if (couponRepository.existsByIdAndCustomersId(couponID, customerId)) {
                throw new CouponSystemException("Coupon with ID [" + couponID + "] was already purchased" +
                        " by the customer with ID[" + customerId + "]");
            }
            Coupon coupon1 = couponRepository.findById(couponID).get();
            coupon1.addCustomer(customerRepository.getById(customerId));
            coupon1.setAmount(coupon1.getAmount() - 1);
            couponRepository.save(coupon1);
            return true;
        } catch (Throwable e) {
            throw new CouponSystemException("Purchase coupon has failed because: " + e.getMessage());
        }
    }

    /**
     * this method is to be used by the "isCouponPurchasable" method
     *
     * @param couponId the coupon id needed to be checked in the database
     * @return true if it exists and false if it is not
     */
    private boolean isCouponExist(int couponId) {
        return couponRepository.existsById(couponId);
    }


    /**
     * this method is used for checking of a customer can buy a coupon.
     * it's only use is in the method "purchaseCoupon" in this class.
     *
     * @param couponID the coupon id needed to be checked in the database
     * @throws CouponSystemException * <p>1) if the coupon does not exist</p>
     *                               * <p>2)if the coupon is out of stock</p>
     *                               * <p>3)if the coupon is not yet available for purchase</p>
     *                               * <p>4)if the coupon has passed it's expiration date</p>
     */
    private void isCouponPurchasable(int couponID) throws CouponSystemException {
        if (isCouponExist(couponID)) {
            Coupon coupon = couponRepository.getById(couponID);
            if (!LocalDate.now().isAfter(coupon.getEndDate())) {
                if (!coupon.getStartDate().isAfter(LocalDate.now())) {
                    if (!(0 < coupon.getAmount())) {
                        throw new CouponSystemException("This coupon is out of stock");
                    }
                } else {
                    throw new CouponSystemException("the coupon is not yet available for purchase");
                }
            } else {
                throw new CouponSystemException("The coupon has passed expiration date");
            }
        } else {
            throw new CouponSystemException("there isn't a coupon with that id in the database");
        }
    }


    /**
     * this method is used for getting all of the customers coupons
     *
     * @return a sorted list of coupons by their ID
     * @throws CouponSystemException <p>1) if there is no relevant coupons purchased by the client</p>
     *                               <p>2) if something went wrong with accessing the database</p>
     */
    public List<Coupon> getAllCoupons(int customerId) throws CouponSystemException {
        try {
            List<Coupon> coupons = customerRepository.findById(customerId).get().getCoupons();
            if (coupons.size() == 0) {
                throw new CouponSystemException("there aren't any coupons purchased by client id[" + customerId + "]");
            }
            return coupons;
        } catch (Throwable e) {
            throw new CouponSystemException("Get all Coupons has failed because: " + e.getMessage());
        }
    }


    /**
     * this method use is to get all of the customer coupons from a certain category
     *
     * @param category the category of the coupons needed to extract
     * @return a sorted list of coupons by their category
     * @throws CouponSystemException <p>1)if there isn't any coupons of that category that belongs to this customer</p>
     *                               <p>2)if something goes wrong in the database</p>
     */
    public List<Coupon> getAllCouponsByCategory(Category category, int customerId) throws CouponSystemException {
        try {
            List<Coupon> coupons = couponRepository.findAllByCustomersIdAndCategory(customerId, category);
            if (coupons.size() == 0) {
                throw new CouponSystemException("there isn't any coupons of that category" +
                        " that belongs to this customer");
            }
            return coupons;
        } catch (Throwable e) {
            throw new CouponSystemException("getting all coupons by category has failed because: " + e.getMessage());
        }
    }


    /**
     * this method is for getting all of the customer coupons up to a max price
     *
     * @param maxPrice the maximum price of the coupons wanted
     * @return the list of the coupons sorted by the price
     * @throws CouponSystemException <p>1)if there isn't any coupons belong to this customer
     *                               under the maximum price</p>
     *                               <p>2)if something goes wrong in the database</p>
     */
    public List<Coupon> getAllCouponsByMaxPrice(double maxPrice, int customerId) throws CouponSystemException {
        try {
            List<Coupon> coupons = couponRepository.findAllByCustomersIdAndPriceLessThanEqual(customerId, maxPrice);
            if (coupons.size() == 0) {
                throw new CouponSystemException("there isn't any coupons lower then the price: " +
                        maxPrice + " that belongs to this customer");
            }
            return coupons;
        } catch (Throwable e) {
            throw new CouponSystemException("Getting all coupons By max price has failed because: " + e.getMessage());
        }
    }

    /**
     * this method is used for getting all of the customer details
     *
     * @return an Object of the class Customer
     * @throws CouponSystemException <p>1)if something goes wrong in the database</p>
     */
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        try {
            return customerRepository.findById(customerId).get();
        } catch (Throwable e) {
            throw new CouponSystemException("Get Customer details has failed because: " + e.getMessage());
        }
    }


}
