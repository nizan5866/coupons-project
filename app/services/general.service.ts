import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Category, Coupon } from '../models/coupon';
import { NewCustomerRegistration } from '../models/new-customer-registration';
import { NewCompanyRegistration } from '../models/new-company-registration';
import { CommonModule } from '@angular/common';  




@Injectable({
  providedIn: 'root'
})
export class GeneralService {

  constructor(private router: Router, private httpClient: HttpClient) { }


  public login(email: string, password: string, clientType: string): Observable<string> {
    let url = "http://localhost:8080/login";
    let paramss = new HttpParams().set('clientType', clientType).set('email', email).set('password', password);
    return this.httpClient.post(url, null, { responseType: 'text', params: paramss });
  }

  public getAllCoupons(amount: number, companyName: string, category: string, maxPrice: number, endDate: string): Observable<Coupon[]> {
    let url = "http://localhost:8080/login/get-all-coupons";
    let paramss = new HttpParams().set('amountGreaterThan', amount).set('companyName', companyName).set('category', category).set('maxPrice', maxPrice)
      .set('endDate',endDate)
    return this.httpClient.get<Coupon[]>(url,{params: paramss});
  }

  public getAllCompaniesName(): Observable<string[]> {
    return this.httpClient.get<string[]>("http://localhost:8080/login/get-all-companies-name");
  }

  public newCustomerRegistrationRequest(ncr: NewCustomerRegistration): Observable<boolean> {
    return this.httpClient.post<boolean>("http://localhost:8080/login/new-customer-registration-request", ncr);
  }

  public newCompanyRegistrationRequest(ncr: NewCompanyRegistration): Observable<boolean> {
    return this.httpClient.post<boolean>("http://localhost:8080/login/new-company-registration-request", ncr);
  }



}
