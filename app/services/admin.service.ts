import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NONE_TYPE } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Company } from '../models/company';
import { Customer } from '../models/customer';
import { NewCompanyRegistration } from '../models/new-company-registration';
import { NewCustomerRegistration } from '../models/new-customer-registration';

@Injectable({
  providedIn: 'root'
})
export class AdminService implements CanActivate {

  public newCustomersRegistrationList?: NewCustomerRegistration[];
  public newCompaniesRegistrationList?: NewCompanyRegistration[];
  public companies?: Company[];
  public customers?: Customer[];
  private isAdmin: boolean = false;


  constructor(private router: Router, private httpClient: HttpClient) { }

  public getCompany(companyId: number): Company | null | undefined {
    let wantedCompany: Company;
    if (this.companies == undefined) {
      return null;
    }
    for (let company of this.companies) {
      if (company.id == companyId) {
        return company;
      }
    }
    return null;
  }

  public getCustomer(customerId: number): Customer | null | undefined {
    let wantedCustomer: Customer;
    if (this.customers == undefined) {
      return null;
    }
    for (let customer of this.customers) {
      if (customer.id == customerId) {
        return customer;
      }
    }
    return null;
  }

  public getNewCompanyRequest(newCompanyRequestId: number): NewCompanyRegistration | null | undefined {
    let wantedRequest: NewCompanyRegistration;
    if (this.newCompaniesRegistrationList == undefined) {
      return null;
    }
    for (let request of this.newCompaniesRegistrationList) {
      if (request.id == newCompanyRequestId) {
        return request;
      }
    }
    return null;
  }

  public getNewCustomerRequest(newCustomerRequestId: number): NewCompanyRegistration | null | undefined {
    let wantedRequest: NewCustomerRegistration;
    if (this.newCustomersRegistrationList == undefined) {
      return null;
    }
    for (let request of this.newCustomersRegistrationList) {
      if (request.id == newCustomerRequestId) {
        return request;
      }
    }
    return null;
  }

  public setIsAdmin(choose: boolean): void {
    this.isAdmin = choose;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate([""]);
      return false;
    }
  }

  public isLoggedIn(): boolean {
    return sessionStorage.getItem("token") != null && this.isAdmin;
  }

  public logout(): void {
    sessionStorage.removeItem("token");
    this.isAdmin = false;
    this.router.navigate([""]);
  }

  public getAllCompanies(): Observable<Company[]> {
    let url: string = "http://localhost:8080//api/admin//get-all-companies"
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.get<Company[]>(url, { headers: headers });
  }

  public fillCompanies(): void {
    this.getAllCompanies().subscribe(
      (companies) => this.companies = companies,
      (error) => this.companies = new Array()
    )
  }

  public getAllCustomers(): Observable<Customer[]> {
    let url: string = "http://localhost:8080//api/admin//get-all-customers"
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.get<Customer[]>(url, { headers: headers });
  }

  public fillCustomers(): void {
    this.getAllCustomers().subscribe(
      (customers) => this.customers = customers,
      (error) => this.customers = new Array()
    )
  }

  public getNewCompaniesRegistration(): Observable<NewCompanyRegistration[]> {
    let url: string = "http://localhost:8080//api/admin/get-all-new-company-registration-requests";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.get<NewCompanyRegistration[]>(url, { headers: headers });
  }

  public getNewCustomersRegistration(): Observable<NewCustomerRegistration[]> {
    let url: string = "http://localhost:8080//api/admin/get-all-new-customer-registration-requests";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.get<NewCustomerRegistration[]>(url, { headers: headers });
  }

  public approveNewCompanyRequest(newCompanyRequest: NewCompanyRegistration): Observable<boolean> {
    let url: string = "http://localhost:8080//api/admin/approve-new-company-registration-request";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.post<boolean>(url, newCompanyRequest, { headers: headers });
  }

  public approveNewCustomerRequest(newCustomerRequest: NewCustomerRegistration): Observable<boolean> {
    let url: string = "http://localhost:8080//api/admin/approve-new-customer-registration-request";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.post<boolean>(url, newCustomerRequest, { headers: headers });
  }

  public updateCompany(companyToUpdate: Company): Observable<boolean> {
    let url: string = "http://localhost:8080//api/admin/update-company";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string)
    return this.httpClient.put<boolean>(url, companyToUpdate, { headers: headers });
  }

  public updateCustomer(customerToUpdate: Customer): Observable<boolean> {
    let url: string = "http://localhost:8080//api/admin/update-customer";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string)
    return this.httpClient.put<boolean>(url, customerToUpdate, { headers: headers });
  }

  public deleteCompany(companyId: number): Observable<boolean> {
    let url: string = "http://localhost:8080//api/admin/delete-company" + companyId;
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.delete<boolean>(url, { headers: headers });
  }

  public deleteCustomer(customerId: number): Observable<boolean> {
    let url: string = "http://localhost:8080//api/admin/delete-customer" + customerId;
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.delete<boolean>(url, { headers: headers });
  }



  public fillLists() {
    this.getNewCompaniesRegistration().subscribe(
      (newCompaniesRegistrationList) => this.newCompaniesRegistrationList = newCompaniesRegistrationList,
      (error) => this.newCompaniesRegistrationList = new Array()
    )
    this.getNewCustomersRegistration().subscribe(
      (newCustomerRegistrationsList) => this.newCustomersRegistrationList = newCustomerRegistrationsList,
      (error) => this.newCustomersRegistrationList = new Array()
    )
  }

}
