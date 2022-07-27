import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-layout',
  templateUrl: './customer-layout.component.html',
  styleUrls: ['./customer-layout.component.css']
})
export class CustomerLayoutComponent implements OnInit {

  constructor(public customerService:CustomerService) { }

  ngOnInit(): void {
    this.customerService.fillCustomerDetails();
    this.customerService.fillCustomerCoupons();
  }

  public logout(){
    this.customerService.logout();
  }

}
