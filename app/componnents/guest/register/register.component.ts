import { Component, OnInit } from '@angular/core';
import { NewCompanyRegistration } from 'src/app/models/new-company-registration';
import { NewCustomerRegistration } from 'src/app/models/new-customer-registration';
import { GeneralService } from 'src/app/services/general.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public emailForRegister?: string;
  public passwordForRegister?: string;
  public passwordVerification?: string;
  public nameForRegister?: string;
  public lastNameForRegister?: string;

  public passwordVerification2?: string;

  public about?: string;



  constructor(private generalService: GeneralService) { }

  ngOnInit(): void {
  }

  // this.generalService.login(email,password,clientType).subscribe(
  //   (token)=>{
  //     sessionStorage.setItem('token',token);
  //     this.navigateToRelevantLayout(clientType);
  //   },
  //   (err)=>{
  //     console.dir(err)
  //   }
  // )

  public sendRegistration(clientType: string) {
    switch (clientType) {
      case "COMPANY":
        let newCompanyRegistration: NewCompanyRegistration =
          new NewCompanyRegistration(0, this.nameForRegister, this.emailForRegister, this.passwordForRegister,
            this.about, new Date());
        this.generalService.newCompanyRegistrationRequest(newCompanyRegistration).subscribe(
          (answer) => alert("the registration request was sent successfully"),
          (error) => alert(error.error.message)
        )
        break;
      case "CUSTOMER":
        let newCustomerRegistration:NewCustomerRegistration =
        new NewCustomerRegistration(0, this.nameForRegister, this.lastNameForRegister, 
          this.emailForRegister, this.passwordForRegister, new Date());
          this.generalService.newCustomerRegistrationRequest(newCustomerRegistration).subscribe(
            (answer) => alert("the registration request was sent successfully"),
            (error) => alert(error.error.message)
          )
    }
  }

  public clearAllFields(){
    this.emailForRegister = undefined;
    this.passwordForRegister = undefined;
    this.passwordVerification = undefined;
    this.nameForRegister = undefined;
    this.lastNameForRegister = undefined;
    this.passwordVerification2 = undefined;
    this.about = undefined;
  }

}
