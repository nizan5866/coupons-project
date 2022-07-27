import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Coupon } from 'src/app/models/coupon';
import { GeneralService } from 'src/app/services/general.service';

@Component({
  selector: 'app-coupons2',
  templateUrl: './coupons2.component.html',
  styleUrls: ['./coupons2.component.css']
})
export class Coupons2Component implements OnInit {

  public companiesName?:string[];
  public couponsList?: Coupon[] | null;
  // public filteredCoupons?: Coupon[];
  public applyAmountBelowZero: boolean = false;

  // public filteredCoupons?: Coupon[];


  // constructor(private id: number, public companyName: string, public category: Category,
  //   public title: string, public startDate: Date, public endDate: Date, public amout: number,
  //   public price: number, public image: string) { }

  constructor(private generalService: GeneralService, private title: Title) { }


  ngOnInit(): void {
    this.title.setTitle("Coupons");
    this.generalService.getAllCompaniesName().subscribe(
      (companiesName) => {
        this.companiesName = companiesName;
      },
      (error) => alert(error.error.message)
    )
    this.getFilteredCoupons("","","","");
    // this.generalService.getAllCoupons().subscribe(
    //   (coupons) => {
    //     this.coupons = coupons;
    //   },
    //   (error) => {
    //     console.dir(error);
    //   }
    // )
  }

  // public getFilteredCoupons2(companyName:string, category: string, maxPriceFilter: string, endDate: string): void{
  //   let amount:number = 0;
  //   let maxPrice:number;
  //   if(maxPriceFilter == ""){
  //     maxPrice = 0;
  //   }else{
  //     maxPrice = maxPriceFilter as unknown as number;
  //   }
  //   if(this.applyAmountBelowZero){
  //     amount = -1;
  //   }
  //   this.filteredCoupons = this.couponsList?.map(coupon =>{

  //   })

  // }


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
