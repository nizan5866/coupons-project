import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Coupon } from 'src/app/models/coupon';
import { CustomerService } from 'src/app/services/customer.service';
import { GeneralService } from 'src/app/services/general.service';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {

  public companiesName?:string[];
  public couponsList?: Coupon[] | null;
  // public filteredCoupons?: Coupon[];
  public applyAmountBelowZero: boolean = false;

  constructor(private generalService: GeneralService,private customerService: CustomerService,
     private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle("Coupons");
    this.generalService.getAllCompaniesName().subscribe(
      (companiesName) => {
        this.companiesName = companiesName;
      },
      (error) => alert(error.error.message)
    )
    this.getFilteredCoupons("","","","");
  }

  public getFilteredCoupons(companyName:string, category: string, maxPriceFilter: string, endDate: string): void{
    let amount:number = 0;
    let maxPrice:number;
    if(maxPriceFilter == ""){
      maxPrice = 0;
    }else{
      maxPrice = maxPriceFilter as unknown as number;
    }
    if(this.applyAmountBelowZero){
      amount = -1;
    }
    this.generalService.getAllCoupons(amount, companyName , category, maxPrice, endDate).subscribe(
      (coupons) => {
        this.couponsList = coupons;
      },
      (error) => {
        alert(error.error.message);
      }
    )
  }

}

