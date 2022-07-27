import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayoutComponent } from './componnents/admin/admin-layout/admin-layout.component';
import { EditCompanyComponent } from './componnents/admin/edit-company/edit-company.component';
import { EditCustomerComponent } from './componnents/admin/edit-customer/edit-customer.component';
import { ViewComponent } from './componnents/admin/view/view.component';
import { CompanyLayoutComponent } from './componnents/company/company-layout/company-layout.component';
import { EditComponent } from './componnents/company/edit/edit.component';
import { CustomerLayoutComponent } from './componnents/customer/customer-layout/customer-layout.component';
import { CompanyDetailsComponent } from './componnents/details/company-details/company-details.component';
import { CouponDetailsComponent } from './componnents/details/coupon-details/coupon-details.component';
import { CustomerDetailsComponent } from './componnents/details/customer-details/customer-details.component';
import { GuestLayoutComponent } from './componnents/guest/guest-layout/guest-layout.component';
import { Page404Component } from './componnents/page404/page404.component';
import { CompanyCouponsComponent } from './componnents/company/company-coupons/company-coupons.component';
import { CustomerCouponsComponent } from './componnents/customer/customer-coupons/customer-coupons.component';
import { PurchaseComponent } from './componnents/customer/purchase/purchase.component';
import { RegisterComponent } from './componnents/guest/register/register.component';
import { CompaniesComponent } from './componnents/guest/companies/companies.component';
import { AdminService } from './services/admin.service';
import { CompanyService } from './services/company.service';
import { CustomerService } from './services/customer.service';
import { Coupons2Component } from './componnents/guest/coupons2/coupons2.component';
import { ApproveCompanyRequestComponent } from './componnents/admin/approve-company-request/approve-company-request.component';
import { ApproveCustomerRequestComponent } from './componnents/admin/approve-customer-request/approve-customer-request.component';
import { ViewCompaniesComponent } from './componnents/admin/view-companies/view-companies.component';
import { ViewCustomersComponent } from './componnents/admin/view-customers/view-customers.component';
import { AboutComponent } from './componnents/guest/about/about.component';
import { CouponDetails2Component } from './componnents/customer/coupon-details2/coupon-details2.component';


//export PATH=~/.npm-global/bin:$PATH

const routes: Routes = [

  {path: "guest", component: GuestLayoutComponent, children: [
    {path: "register", component: RegisterComponent},
    {path: "company-details/:id", component:CompanyDetailsComponent},
    {path: "coupon-details/:id", component:CouponDetailsComponent},
    {path: "coupons", component:Coupons2Component},
    {path: "companies", component: CompaniesComponent},
    {path: "about", component: AboutComponent}
  ]},

  {
    path: "admin", canActivate: [AdminService], component: AdminLayoutComponent, children: [
      {path: "approve-company-request/:id", component: ApproveCompanyRequestComponent},
      {path: "approve-customer-request/:id", component: ApproveCustomerRequestComponent},
      {path: "view-companies", component: ViewCompaniesComponent},
      {path: "view-customers", component: ViewCustomersComponent},
      {path: "view", component:ViewComponent},
      {path: "edit-company/:id", component:EditCompanyComponent},
      {path: "edit-customer/:id", component:EditCustomerComponent}
    ]
  },

  {
    path: "company", canActivate: [CompanyService], component: CompanyLayoutComponent, children: [
      {path: "coupons", component:CompanyCouponsComponent},
      {path: "edit", component:EditComponent},
      {path: "coupon-details1/:id", component:CouponDetailsComponent}
    ]
  },

  {path: "customer", canActivate: [CustomerService], component: CustomerLayoutComponent, children: [
    {path: "coupons", component:CustomerCouponsComponent},
    {path: "purchase", component: PurchaseComponent},
    {path: "coupon-details2/:id", component:CouponDetails2Component},
  ]},

  { path: '', redirectTo: "/guest", pathMatch: "full" },
  { path: "**", component: Page404Component },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

//export PATH=~/.npm-global/bin:$PATH

