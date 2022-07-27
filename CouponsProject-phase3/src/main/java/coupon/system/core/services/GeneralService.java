package coupon.system.core.services;

import coupon.system.core.entity_beans.NewCompanyRegistration;
import coupon.system.core.entity_beans.NewCustomerRegistration;
import coupon.system.core.exception.CouponSystemException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GeneralService extends ClientService {

    /**
     * this method is for customer wants to register. it checks if there is a customer
     * with that same email.
     *
     * @param ncr the object of that relevant type
     * @return true if the application was submitted
     */
    public boolean customerRegistrationRequest(NewCustomerRegistration ncr) throws CouponSystemException {
        if (!customerRepository.existsByEmail(ncr.getEmail()) && !nCustomerRegRep.existsByEmail(ncr.getEmail())) {
            nCustomerRegRep.save(ncr);
            return true;
        } else {
            throw new CouponSystemException("Customer with the same email already exists");
        }
    }

    /**
     * this method is used for new companies to register for the website
     *
     * @param ncr the object of the relevant type
     * @return true if the application was submitted
     * @throws CouponSystemException <p>1) if there is company with the same email</p>
     *                               <p>2)if there is a company with the same name</p>
     */
    public boolean companyRegistrationRequest(NewCompanyRegistration ncr) throws CouponSystemException {
        if (!companyRepository.existsByEmail(ncr.getEmail()) && !nCompanyRegRep.existsByEmail(ncr.getEmail())) {
            if (!companyRepository.existsByName(ncr.getName())) {
                nCompanyRegRep.save(ncr);
                return true;
            } else {
                throw new CouponSystemException("Company with the same name already exists");
            }
        } else {
            throw new CouponSystemException("Company with same email already exists");
        }
    }


}
