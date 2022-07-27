import { Injectable } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyService implements CanActivate{

  private isCompany:boolean = false;

  constructor(private router: Router) { }

  public getIsCompany(): boolean{
    return this.isCompany;
  }

  public setIsCompany(choose:boolean): void{
    this.isCompany = choose;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    if (this.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate([""]);
      return false;
    }
  }

  public isLoggedIn(): boolean {
    return sessionStorage.getItem("token") != null && this.isCompany;
  }

  public logout(): void{
    sessionStorage.removeItem("token");
    this.isCompany = false;
    this.router.navigate([""]);
  }

}
