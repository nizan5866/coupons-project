import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';
import { CompanyService } from 'src/app/services/company.service';
import { CustomerService } from 'src/app/services/customer.service';
import { GeneralService } from 'src/app/services/general.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private generalService:GeneralService, private adminService:AdminService,
    private companyService: CompanyService, private customerService:CustomerService, private router: Router) { }

    
  ngOnInit(): void {
  }

  public login(email:string, password:string, clientType:string):  void{
    this.generalService.login(email,password,clientType).subscribe(
      (token)=>{
        sessionStorage.setItem('token',token);
        this.navigateToRelevantLayout(clientType);
      },
      (err)=>alert(err.error.message)
    )
  }

  private navigateToRelevantLayout(clientType:string): void{
    switch(clientType){
      case "ADMINISTRATOR":
        this.adminService.setIsAdmin(true);
        this.router.navigate(["admin"]);
        break;
      case "COMPANY":
        this.companyService.setIsCompany(true);
        this.router.navigate(["company"]);
        break;
      case "CUSTOMER":
        this.customerService.setIsCustomer(true);
        this.router.navigate(["customer"])
        break;
    }
  }



}
