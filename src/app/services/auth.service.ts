import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Admin } from './admin';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private urlBase = "http://localhost:9010/admin/pass";
  private httpCli : HttpClient;  
  constructor(httpCli: HttpClient) { 
    this.httpCli = httpCli;
  }

  // public getAdmin(): Observable<Admin[]>{
  //   const httpHead={
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json',
  //       'Access-Control-Allow-Origin':'*'
  //     })
  //   };
  //   return this.httpCli.get<Admin[]>(this.urlBase, httpHead);
  // }

  authenticate(admin) : Observable<Admin>{
      const httpHead={
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin':'*'
        })
      };
    //  sessionStorage.setItem("userName", admin);
     return this.httpCli.post<Admin>(this.urlBase, admin, httpHead);
  }
 
  isUserLoggedIn() {
    let admin2 = sessionStorage.getItem('admin')
    // console.log('admin2 '+admin2);
    // let jsonAdmin2 = JSON.parse(admin2); 
    // console.log('jsonAdmin2 '+jsonAdmin2);   
    if(admin2==null || admin2=='null')
    { return false;
    }
    else{
      return true;
    }
    
  }

  logOut() {
    sessionStorage.setItem('admin',null) ;
  }
}
