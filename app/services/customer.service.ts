import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Coupon } from '../models/coupon';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService implements CanActivate{

  private isCustomer:boolean = false;
  public customer:Customer = new Customer(0,"null","null","null@null","12");
  public customerCoupons?:Coupon[];

  constructor(private router: Router, private httpClient:HttpClient) { }

  public getIsCustomer(): boolean{
    return this.isCustomer;
  }


  public setIsCustomer(choose:boolean): void{
    this.isCustomer = choose;
  }


  canActivate(router: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    if (this.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate([""]);
      return false;
    }
  }

  public getCoupon(couponId: number): Coupon | null | undefined {
    let wantedCoupon: Coupon;
    if (this.customerCoupons == undefined) {
      return null;
    }
    for (let coupon of this.customerCoupons) {
      if (coupon.id == couponId) {
        return coupon;
      }
    }
    return null;
  }


  public isLoggedIn(): boolean {
    return sessionStorage.getItem("token") != null && this.isCustomer;
  }

  public logout(): void{
    sessionStorage.removeItem("token");
    this.isCustomer = false;
    this.router.navigate([""]);
  }


  public fillCustomerDetails(){
    this.getCustomrDetails().subscribe(
      (answear)=> this.customer = answear,
      (err) => {
        alert("sorry, something went wrong");
        this.router.navigate([""]);
      }
    )
  }

  public fillCustomerCoupons(){
    this.getCustomerCoupons().subscribe(
      (coupons) => this.customerCoupons = coupons,
      (err) => this.customerCoupons = new Array()
    )
  }

  public purchaseCoupon(coupon:Coupon): Observable<boolean>{
    let url:string = "http://localhost:8080//api/customer/purchase-coupon";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.put<boolean>(url,coupon,{headers: headers})
  }

  private getCustomrDetails(): Observable<Customer>{
    let url:string = "http://localhost:8080//api/customer/get-customer-details";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.get<Customer>(url, { headers: headers })
  }

  private getCustomerCoupons(): Observable<Coupon[]>{
    let url:string = "http://localhost:8080//api/customer/get-all-coupons";
    let headers: HttpHeaders = new HttpHeaders().set('token', sessionStorage.getItem("token") as unknown as string);
    return this.httpClient.get<Coupon[]>(url, { headers: headers });
  }
}
