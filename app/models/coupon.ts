import { Company } from "./company";
import { CommonModule } from '@angular/common';  

export class Coupon {

    constructor(public id:number, public company:Company, public category:Category,
        public title:string, public description:string, public startDate:Date, public endDate:Date, public amout:number,
        public price:number, public image:string){}

    // constructor(private id: number, public companyName: string, public category: Category,
    //     public title: string, public startDate: Date, public endDate: Date, public amout: number,
    //     public price: number, public image: string) { }


}

export enum Category {
    COMPUTERS,
    SMARTPHONES,
    SOFTWARE,
    CLOUD_STORAGE
}


