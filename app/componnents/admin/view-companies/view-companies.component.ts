import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-view-companies',
  templateUrl: './view-companies.component.html',
  styleUrls: ['./view-companies.component.css']
})
export class ViewCompaniesComponent implements OnInit {

  constructor(public adminService:AdminService) { }

  ngOnInit(): void {
    this.adminService.fillCompanies();
  }

}
