import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NewCompanyRegistration } from 'src/app/models/new-company-registration';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-approve-company-request',
  templateUrl: './approve-company-request.component.html',
  styleUrls: ['./approve-company-request.component.css']
})
export class ApproveCompanyRequestComponent implements OnInit {

  public newCompanyRequest?: NewCompanyRegistration;

  constructor(private activatedRoute: ActivatedRoute, private adminService: AdminService,
    private router:Router) { }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot);
    let id = this.activatedRoute.snapshot.params.id;
    let tryTest = this.adminService.getNewCompanyRequest(id);
    if (tryTest != null && tryTest != undefined) {
      this.newCompanyRequest = tryTest;
    } else {
      alert("error has occured please try again");
    }
  }

  public approveNewCompanyRequest() {
    if (this.newCompanyRequest != undefined) {
      this.adminService.approveNewCompanyRequest(this.newCompanyRequest).subscribe(
        (boolean) => {
          this.adminService.fillLists();
          alert("company was added. well done!")
          this.router.navigate(["/admin/view"])
        },
        (error) => alert(error.error.message)
      )
    }
  }

}
