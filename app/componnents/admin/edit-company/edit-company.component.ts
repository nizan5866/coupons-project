import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/models/company';
import { NewCompanyRegistration } from 'src/app/models/new-company-registration';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {
  public company?:Company;


  constructor(private activatedRoute:ActivatedRoute, private adminService:AdminService,
    private router:Router) { }

  ngOnInit(): void {
    console.log(this.activatedRoute.snapshot);
    let id = this.activatedRoute.snapshot.params.id;
    let tryTest = this.adminService.getCompany(id);
    if (tryTest != null && tryTest != undefined) {
      this.company = tryTest;
    } else {
      alert("error has occured please try again");
    }
  }

  public updateCompany() {
    if (this.company != undefined) {
      this.adminService.updateCompany(this.company).subscribe(
        (boolean) => {
          alert("company got an update. well done!");
          this.router.navigate(["/admin/view-companies"]);
        },
        (error) => alert(error.error.message)
      )
    }
  }

  public deleteCompany() {
    if (this.company?.id != undefined) {
      this.adminService.deleteCompany(this.company.id).subscribe(
        (boolean) => {
          this.adminService.fillCompanies();
          alert("company got deleted. well done!");
          this.router.navigate(["/admin/view-companies"]);
        },
        (error) => alert(error.error.message)
      )
    }
  }



}
