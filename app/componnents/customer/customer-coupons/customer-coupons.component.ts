import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-coupons',
  templateUrl: './customer-coupons.component.html',
  styleUrls: ['./customer-coupons.component.css']
})
export class CustomerCouponsComponent implements OnInit {
  public companiesName?:string[];
  public couponsList?: Coupon[] | null;
  // public filteredCoupons?: Coupon[];

  constructor(public customerService:CustomerService) { }

  ngOnInit(): void {
  }

  public getFilteredCoupons(companyName:string, category:string, endDate: string){}

}
