import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { GeneralService } from 'src/app/services/general.service';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {

  public companiesName?:string[];

  constructor(private generalService: GeneralService, private title:Title) { }

  ngOnInit(): void {
    this.title.setTitle("Companies Name");
    this.generalService.getAllCompaniesName().subscribe(
      (companiesName) => {
        this.companiesName = companiesName;
      },
      (error) => alert(error.error.message)
    )
  }

}
