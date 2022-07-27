import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { Customer } from 'src/app/models/customer';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  public customer?:Customer;


  constructor(private activatedRoute:ActivatedRoute, private adminService:AdminService,
     private router:Router) { }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot);
    let id = this.activatedRoute.snapshot.params.id;
    let tryTest = this.adminService.getCustomer(id);
    if (tryTest != null && tryTest != undefined) {
      this.customer = tryTest;
    } else {
      alert("error has occured please try again");
    }
  }

  public updateCustomer() {
    if (this.customer != undefined) {
      this.adminService.updateCustomer(this.customer).subscribe(
        (boolean) => {
          alert("customer got an update. well done!")
          this.router.navigate(["/admin/view-customers"]);
        },
        (error) => alert(error.error.message)
      )
    }
  }

  public deleteCustomer() {
    if (this.customer?.id != undefined) {
      this.adminService.deleteCustomer(this.customer.id).subscribe(
        (boolean) => {
          this.adminService.fillCustomers();
          alert("customer got deleted. well done!")
          this.router.navigate(["/admin/view-customers"]);
        },
        (error) => alert(error.error.message)
      )
    }
  }

}
