import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NewCustomerRegistration } from 'src/app/models/new-customer-registration';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-approve-customer-request',
  templateUrl: './approve-customer-request.component.html',
  styleUrls: ['./approve-customer-request.component.css']
})
export class ApproveCustomerRequestComponent implements OnInit {


  public newCustomerRequest?: NewCustomerRegistration;

  constructor(private activatedRoute: ActivatedRoute, private adminService: AdminService,
    private router:Router) { }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot);
    let id = this.activatedRoute.snapshot.params.id;
    let tryTest = this.adminService.getNewCustomerRequest(id);
    if (tryTest != null && tryTest != undefined) {
      this.newCustomerRequest = tryTest;
    } else {
      alert("error has occured please try again");
    }
  }

  public approveNewCustomerRequest() {
    if (this.newCustomerRequest != undefined) {
      this.adminService.approveNewCustomerRequest(this.newCustomerRequest).subscribe(
        (boolean) => {
          this.adminService.fillLists();
          alert("customer was added. well done!");
          this.router.navigate(["admin/view"]);
        },
        (error) => alert(error.error.message)
      )
    }
  }

}
