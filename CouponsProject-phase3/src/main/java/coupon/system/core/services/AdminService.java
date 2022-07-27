package coupon.system.core.services;

import coupon.system.core.entity_beans.Company;
import coupon.system.core.entity_beans.Customer;
import coupon.system.core.entity_beans.NewCompanyRegistration;
import coupon.system.core.entity_beans.NewCustomerRegistration;
import coupon.system.core.exception.CouponSystemException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminService extends ClientService {


    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && (password.equals("admin"));
    }


    /**
     * this method is used to add a new company to the data base.
     * if the company name AND email is uniq, and there isn't any null
     * argument, the company will be added to the SQL database.
     *
     * @param company an object from the class Company
     * @throws CouponSystemException if something of the mentioned above has occurred
     */
    public void addCompany(Company company) throws CouponSystemException {
        try {
            boolean checkerEmail = companyRepository.existsByEmail(company.getEmail());
            boolean checkerName = companyRepository.existsByName(company.getName());
            if (!checkerEmail && !checkerName) {
                companyRepository.save(company);
            } else if (checkerEmail && checkerName) {
                throw new CouponSystemException("company/ies with same Email AND the same name already exists");
            } else if (checkerName) {
                throw new CouponSystemException("company with the same name already exists");
            } else {
                throw new CouponSystemException("company with the same Email already exists");
            }
        } catch (CouponSystemException e) {
            throw new CouponSystemException("cannot add that company because: " + e.getMessage());
        }
    }

    /**
     * this method is used to update an existing company (that doesnt exists in the database)
     *
     * @param company an object from the class Company
     * @throws CouponSystemException throws if the company name has changed, or there is a problem with accessing
     *                               the database
     * @return true if succeeded
     */
    public boolean updateCompany(Company company) throws CouponSystemException {
        try {
            Company companyToUpdate = companyRepository.getById(company.getId());
            if (!companyToUpdate.getName().equals(company.getName())) {
                throw new RuntimeException("Cannot change company name");
            }
            if (!companyToUpdate.getEmail().equals(company.getEmail())) {
                if (companyRepository.existsByEmail(company.getEmail())) {
                    throw new CouponSystemException("Company with same Email already exists.");
                } else {
                    companyToUpdate.setEmail(company.getEmail());
                }
            }
            companyToUpdate.setPassword(company.getPassword());
            companyRepository.save(companyToUpdate);
            return true;
        } catch (Throwable throwable) {
            throw new CouponSystemException("Update Company failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is used to the delete a company from the SQL database safely.
     * it's also removes all the companies coupons and the company coupons that
     * been purchase by clients.
     *
     * @param companyID Company ID according to the database
     * @throws CouponSystemException if there is a problem with deleting the company
     *                               from the database
     * @return true if succeeded
     */
    public boolean deleteCompany(int companyID) throws CouponSystemException {
        try {
            if (companyRepository.existsById(companyID)) {
                companyRepository.deleteById(companyID);
                return true;
            } else {
                throw new CouponSystemException("Cannot find company with id [" + companyID + "]");
            }
        } catch (Throwable throwable) {
            throw new CouponSystemException("Delete Company failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is used for getting all the companies from the database
     *
     * @return a sorted ArrayList by the companies name from the database
     * @throws CouponSystemException if there is a problem with the database
     */
    public List<Company> getAllCompanies() throws CouponSystemException {
        try {
            return companyRepository.findAll();
        } catch (Throwable throwable) {
            throw new CouponSystemException("Get all Companies failed because: " + throwable.getMessage());
        }
    }


    /**
     * this method is for getting a specific company from the database using company ID
     *
     * @param companyId Company ID according to the database
     * @return an Object from the class Company representing the relevant company from the database
     * @throws CouponSystemException if there is a problem with the database
     */
    public Company getOneCompany(int companyId) throws CouponSystemException {
        try {
            return companyRepository.findById(companyId).get();
        } catch (Throwable throwable) {
            throw new CouponSystemException("Get one Company failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is used to add a customer to the Database
     *
     * @param customer an Object from the class Customer
     * @throws CouponSystemException if there is a problem with adding the customer
     *                               to the database
     */
    public void addCustomer(Customer customer) throws CouponSystemException {
        try {
            if (!customerRepository.existsByEmail(customer.getEmail())) {
                customerRepository.save(customer);
            } else {
                throw new CouponSystemException("This email address is already taken.");
            }
        } catch (Throwable throwable) {
            throw new CouponSystemException("Add new Customer failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is used for updating a customer in the database relying on the given Object ID
     *
     * @param customer an Object from the class Customer
     * @throws CouponSystemException if the client isn't in the data base or there is a problem with the database
     */
    public boolean updateCustomer(Customer customer) throws CouponSystemException {
        try {
            Customer customerToUpdate = customerRepository.getById(customer.getId());
            if (!customerToUpdate.getEmail().equals(customer.getEmail())) {
                if (customerRepository.existsByEmail(customer.getEmail())) {
                    throw new CouponSystemException("This email address is already taken.");
                } else {
                    customerToUpdate.setEmail(customer.getEmail());
                }
            }
            customerToUpdate.setPassword(customer.getPassword());
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setLastName(customer.getLastName());
            customerRepository.save(customerToUpdate);
            return true;
        } catch (Throwable throwable) {
            throw new CouponSystemException("Update Customer failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is used by the administrator to delete a client from the data base
     *
     * @param customerId the customer ID as represented in the database
     * @throws CouponSystemException if the customer is not inside the database or something occurs
     *                               with the data base
     */
    public boolean deleteCustomer(int customerId) throws CouponSystemException {
        try {
            if (customerRepository.existsById(customerId)) {
                customerRepository.deleteById(customerId);
                return true;
            } else {
                throw new CouponSystemException("Cannot find customer with ID [" + customerId + "]");
            }
        } catch (Throwable throwable) {
            throw new CouponSystemException("Delete Customer failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is being used to achieve all of the customers inside the database by the administrator
     *
     * @return a sorted list by last name and then name of all the clients in the database
     * @throws CouponSystemException if the list is empty or there is a problem with the data base
     */
    public List<Customer> getAllCustomers() throws CouponSystemException {
        try {
            return customerRepository.findAll();
        } catch (Throwable throwable) {
            throw new CouponSystemException("Get All Customers failed because: " + throwable.getMessage());
        }
    }

    /**
     * this method is being used to achieve a single customer from the data base based on the
     * customers ID
     *
     * @param customerId the customer ID as represented in the database
     * @return an Object from the class Customer
     * @throws CouponSystemException if there isn't a client with that ID in the database,
     *                               or something has gone wrong in the data base
     */
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        try {
            return customerRepository.findById(customerId).get();
        } catch (Throwable throwable) {
            throw new CouponSystemException("Get one Customer failed because: " + throwable.getMessage());
        }
    }

    /**
     * @return a list of the new companies requests, ordered by registration date
     */
    public List<NewCompanyRegistration> getAllNewCompanyRegistration() {
        return nCompanyRegRep.findAll();
    }

    /**
     * @return a list of the new customers requests, ordered by registration date
     */
    public List<NewCustomerRegistration> getAllNewCustomerRegistration() {
        return nCustomerRegRep.findAll();
    }

    /**
     * this method is used to approve company registration application
     * @param ncr the registration
     * @throws CouponSystemException if there is a problem with adding to company
     */
    public boolean approveCompanyRequest(NewCompanyRegistration ncr) throws CouponSystemException {
        String name = ncr.getName();
        String email = ncr.getEmail();
        String password = ncr.getPassword();
        Company companyToAdd = new Company(0, name, email, password);
        addCompany(companyToAdd);
        nCompanyRegRep.delete(ncr);
        return true;
    }

    /**
     * this method is used to approve customer registration application
     * @param ncr the registration
     * @throws CouponSystemException if there is a problem with adding to customer
     */
    public boolean approveCustomerRequest(NewCustomerRegistration ncr) throws CouponSystemException{
        String firstName = ncr.getFirstName();
        String lastName = ncr.getLastName();
        String email = ncr.getEmail();
        String password = ncr.getPassword();
        Customer customerToAdd = new Customer(0,firstName,lastName,email,password);
        addCustomer(customerToAdd);
        nCustomerRegRep.delete(ncr);
        return true;
    }

}
