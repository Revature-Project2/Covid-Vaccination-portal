import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ICreateOrderRequest, IPayPalConfig } from 'ngx-paypal';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'CovidVaccinePortal';
  toggle = false;

  constructor(public loginService:AuthService, public router: Router) { }

  navigation1() {
    this.toggle = true;
  }
  method()
  {
    sessionStorage.setItem('admin',null)
    
  }

  isInLogin(){
    if(this.router.url === "/login"){
       return true;
    }else{
         return false;
       }
    }
  
 
}
