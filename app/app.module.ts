import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { RootComponent } from './root/root/root.component';
import { AdminLayoutComponent } from './componnents/admin/admin-layout/admin-layout.component';
import { CompanyLayoutComponent } from './componnents/company/company-layout/company-layout.component';
import { CustomerLayoutComponent } from './componnents/customer/customer-layout/customer-layout.component';
import { LoginComponent } from './componnents/guest/login/login.component';
import { RegisterComponent } from './componnents/guest/register/register.component';
import { GuestLayoutComponent } from './componnents/guest/guest-layout/guest-layout.component';
import { CompaniesComponent } from './componnents/guest/companies/companies.component';
import { PurchaseComponent } from './componnents/customer/purchase/purchase.component';
import { EditComponent } from './componnents/company/edit/edit.component';
import { EditCompanyComponent } from './componnents/admin/edit-company/edit-company.component';
import { EditCustomerComponent } from './componnents/admin/edit-customer/edit-customer.component';
import { ViewComponent } from './componnents/admin/view/view.component';
import { CompanyDetailsComponent } from './componnents/details/company-details/company-details.component';
import { CouponDetailsComponent } from './componnents/details/coupon-details/coupon-details.component';
import { CustomerDetailsComponent } from './componnents/details/customer-details/customer-details.component';
import { Page404Component } from './componnents/page404/page404.component';
import { CompanyCouponsComponent } from './componnents/company/company-coupons/company-coupons.component';
import { CustomerCouponsComponent } from './componnents/customer/customer-coupons/customer-coupons.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Coupons2Component } from './componnents/guest/coupons2/coupons2.component';
import { ApproveCompanyRequestComponent } from './componnents/admin/approve-company-request/approve-company-request.component';
import { ApproveCustomerRequestComponent } from './componnents/admin/approve-customer-request/approve-customer-request.component';
import { ViewCompaniesComponent } from './componnents/admin/view-companies/view-companies.component';
import { ViewCustomersComponent } from './componnents/admin/view-customers/view-customers.component';
import { AboutComponent } from './componnents/guest/about/about.component';
import { CouponDetails2Component } from './componnents/customer/coupon-details2/coupon-details2.component';



//export PATH=~/.npm-global/bin:$PATH



@NgModule({
  declarations: [
    RootComponent,
    AdminLayoutComponent,
    CompanyLayoutComponent,
    CustomerLayoutComponent,
    LoginComponent,
    RegisterComponent,
    GuestLayoutComponent,
    CompaniesComponent,
    PurchaseComponent,
    EditComponent,
    EditCompanyComponent,
    EditCustomerComponent,
    ViewComponent,
    CompanyDetailsComponent,
    CouponDetailsComponent,
    CustomerDetailsComponent,
    Page404Component,
    CompanyCouponsComponent,
    CustomerCouponsComponent,
    Coupons2Component,
    ApproveCompanyRequestComponent,
    ApproveCustomerRequestComponent,
    ViewCompaniesComponent,
    ViewCustomersComponent,
    AboutComponent,
    CouponDetails2Component,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [RootComponent]
})
export class AppModule { }

//export PATH=~/.npm-global/bin:$PATH
