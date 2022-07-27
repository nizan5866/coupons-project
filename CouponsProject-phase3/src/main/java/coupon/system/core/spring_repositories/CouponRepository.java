package coupon.system.core.spring_repositories;

import coupon.system.core.entity_beans.Category;
import coupon.system.core.entity_beans.Coupon;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    boolean existsCouponByCompanyIdAndTitle(int companyId, String title);

    boolean existsCouponByCompanyIdAndId(int companyId, int couponId);

    boolean existsByIdAndCustomersId(int couponId, int customerId);

    @OrderBy("title asc")
    List<Coupon> findAllByCompanyId(int companyId);

    @OrderBy("category asc, title asc")
    List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

    @OrderBy("price asc, title asc")
    List<Coupon> findAllByCompanyIdAndPriceLessThanEqual(int companyId, Double maxPrice);

    @OrderBy("category asc")
    List<Coupon> findAllByCustomersIdAndCategory(int customerId, Category category);


    @OrderBy("price asc")
    List<Coupon> findAllByCustomersIdAndPriceLessThanEqual(int customerId, double maxPrice);

    void deleteAllByEndDateBefore(LocalDate date);

    @Override
    List<Coupon> findAll();

//=====================================================================================================================


    //for general service and company service

    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndCategoryAndPriceLessThanEqualAndEndDateIsAfter(
         int amountGreaterThan, String companyName, Category category, double maxPrice, LocalDate endDate);

    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndCategoryAndPriceLessThanEqual(
            int amountGreaterThan, String companyName, Category category, double maxPrice);

    @OrderBy("title asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndCategory(
            int amountGreaterThan, String companyName, Category category);

    @OrderBy("companyName asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyName(
            int amountGreaterThan, String companyName);

    @OrderBy("title asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndEndDateIsAfter(
            int amountGreaterThan, String companyName, LocalDate endDate);

    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndPriceLessThanEqualAndEndDateIsAfter(
            int amountGreaterThan, String companyName, double maxPrice, LocalDate endDate);

    @OrderBy("title asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndCategoryAndEndDateIsAfter(
            int amountGreaterThan, String companyName, Category category, LocalDate endDate);

    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndCompanyNameAndPriceLessThanEqual(
            int amountGreaterThan, String companyName, double maxPrice);


    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndCategoryAndPriceLessThanEqualAndEndDateIsAfter(
            int amountGreaterThan, Category category, double maxPrice, LocalDate endDate);


    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndCategoryAndPriceLessThanEqual(
            int amountGreaterThan, Category category, double maxPrice);

    @OrderBy("title asc")
    List<Coupon> findAllByAmountGreaterThanAndCategoryAndEndDateIsAfter(
            int amountGreaterThan, Category category, LocalDate endDate);

    @OrderBy("companyName asc")
    List<Coupon> findAllByAmountGreaterThanAndCategory(
            int amountGreaterThan, Category category);


    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndPriceLessThanEqual(
            int amountGreaterThan, double maxPrice);

    @OrderBy("price asc")
    List<Coupon> findAllByAmountGreaterThanAndPriceLessThanEqualAndEndDateIsAfter(
            int amountGreaterThan, double maxPrice, LocalDate endDate);

    @OrderBy("endDate asc")
    List<Coupon> findAllByAmountGreaterThanAndEndDateIsAfter(
            int amountGreaterThan, LocalDate endDate);

    @OrderBy("title asc")
    List<Coupon> findAllByAmountGreaterThan(
            int amountGreaterThan);


//=================================================================================================================




//    @OrderBy("price asc")
//    @Query(
//            value = "select * from coupon where id in(select coupon_id from customer_coupon where customer_id = :id)" +
//                    " AND price <= :maxPrice ;",
//            nativeQuery = true
//    )
//    public List<Coupon> findAllCustomerCouponsByMaxPrice(@Param("id") int customerId,@Param("maxPrice") double maxPrice);



    //    @OrderBy("category asc")
//    @Query(
//            value = "select * from coupon where id in(select coupon_id from customer_coupon where customer_id = :id)" +
//                    " AND category = :category ;",
//            nativeQuery = true
//    )
//    public List<Coupon> findAllCustomerCouponsOfCategory
//            (@Param("id") int customerId, @Param("category") int categoryId);


//    @Query(
//            value = "select * from coupon where id in(select coupon_id from customer_coupon where customer_id = :id);",
//            nativeQuery = true
//    )
//    public List<Coupon> findAllCustomerCoupons(@Param("id") int customerId);


//    @Query(
//            value = "select * from customer_coupon where customer_id = :customer_id And coupon_id= :coupon_id ;",
//            nativeQuery = true
//    )
//    public List<?> couponAlreadyPurchased(@Param("customer_id") Integer customer_id, @Param("coupon_id") Integer coupon_id);

}
