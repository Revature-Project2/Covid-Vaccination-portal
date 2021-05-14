import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Admin } from '../services/admin';
import { FormControl, FormGroup } from '@angular/forms';
import { ConditionalExpr } from '@angular/compiler';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {  
  invalidLogin = "yes"; 
  

  adminCreds = new FormGroup({
    userName : new FormControl(''),
    password : new FormControl('')
  });

  constructor(private router: Router,
              private loginservice: AuthService) {}

  ngOnInit() { 
    sessionStorage.setItem('admin', null);    
  }
  
  checkLogin(admin:FormGroup) {
    // sessionStorage.setItem("invalidLogin", this.invalidLogin);
    // console.log('button clicked');
    // console.log('admin in check in method: '+ admin.value)
    let stringAdmin = JSON.stringify(admin.value);
    // console.log('I am in checkLoglin'+ stringAdmin); 

    this.loginservice.authenticate(stringAdmin).subscribe(
      dataResult => {
        console.log('I am in checkLoglin'+ dataResult); 
        console.info(dataResult);       
        if(dataResult != null){
          this.invalidLogin = "no"; 
          sessionStorage.setItem('admin', JSON.stringify(dataResult))
          console.log('logged in');          
          this.router.navigate(['/admin']);
          // this.invalidLogin = false; 
          return false;
        }
        else{
          this.invalidLogin = "yes";
          sessionStorage.setItem('admin', null);
        }
      }
    )   
       
  }

}
