import { CommonModule } from '@angular/common';  


export class NewCompanyRegistration {

    constructor(public id?: number, public name?: string, public email?: string, public password?: string,
        public about?: string, public dateOfRequest?: Date) { }


}
