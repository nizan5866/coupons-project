import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { NewCompanyRegistration } from 'src/app/models/new-company-registration';
import { NewCustomerRegistration } from 'src/app/models/new-customer-registration';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {


  constructor(public adminService: AdminService) { }

  ngOnInit(): void {
    this.adminService.fillLists();
  }
}




