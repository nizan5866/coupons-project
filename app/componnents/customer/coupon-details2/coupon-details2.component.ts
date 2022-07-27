import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-coupon-details2',
  templateUrl: './coupon-details2.component.html',
  styleUrls: ['./coupon-details2.component.css']
})
export class CouponDetails2Component implements OnInit {

  public coupon?: Coupon;

  constructor(private activatedRoute: ActivatedRoute, private customerService: CustomerService,
    private router: Router) { }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot);
    let id = this.activatedRoute.snapshot.params.id;
    let tryTest = this.customerService.getCoupon(id);
    if (tryTest != null && tryTest != undefined) {
      this.coupon = tryTest;
    } else {
      alert("error has occured please try again");
    }
  }

  public purchaseCoupon() {
    if (this.coupon != undefined) {
      this.customerService.purchaseCoupon(this.coupon).subscribe(
        (boolean) => alert("coupon purchased!"),
        (err) => alert(err)
      )
    }
  }

  // public approveNewCustomerRequest() {
  //   if (this.newCustomerRequest != undefined) {
  //     this.adminService.approveNewCustomerRequest(this.newCustomerRequest).subscribe(
  //       (boolean) => {
  //         this.adminService.fillLists();
  //         alert("customer was added. well done!");
  //         this.router.navigate(["admin/view"]);
  //       },
  //       (error) => alert(error.error.message)
  //     )
  //   }
  // }

}
