import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Admin } from '../services/admin';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConditionalExpr } from '@angular/compiler';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {  
  invalidLogin = "yes"; 
  submitted = false;

  adminCreds = new FormGroup({
    userName : new FormControl('',Validators.required),
    password : new FormControl('',Validators.required)
  });

  constructor(private router: Router,
              private loginservice: AuthService) {}

  ngOnInit() { 
    this.submitted = true;
    sessionStorage.setItem('admin', null);    
  }
  
  checkLogin() {
   
    // sessionStorage.setItem("invalidLogin", this.invalidLogin);
    // console.log('button clicked');
    // console.log('admin in check in method: '+ admin.value)
    let stringAdmin = JSON.stringify(this.adminCreds.value);
    // console.log('I am in checkLoglin'+ stringAdmin); 

    this.loginservice.authenticate(stringAdmin).subscribe(
      dataResult => {
 
        console.info(dataResult);       
        if(dataResult != null){
          this.invalidLogin = "no"; 
          ('logged in');          
          this.router.navigate(['/manageclinics']);
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
