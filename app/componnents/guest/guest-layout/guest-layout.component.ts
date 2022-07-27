import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GeneralService } from 'src/app/services/general.service';

@Component({
  selector: 'app-guest-layout',
  templateUrl: './guest-layout.component.html',
  styleUrls: ['./guest-layout.component.css']
})
export class GuestLayoutComponent implements OnInit {

  constructor(private generalService: GeneralService, private router:Router ) { }

  ngOnInit(): void {
    this.router.navigate(["guest/about"])
  }

  // public moveToRegister(): void{
  //   this.router.navigate([""])
  // }



}
