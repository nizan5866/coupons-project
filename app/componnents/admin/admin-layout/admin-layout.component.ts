import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-admin-layout',
  templateUrl: './admin-layout.component.html',
  styleUrls: ['./admin-layout.component.css']
})
export class AdminLayoutComponent implements OnInit {

  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    this.adminService.fillLists();
    this.adminService.fillCompanies();
    this.adminService.fillCustomers();
  }

  public logout(){
    this.adminService.logout();
  }

}
