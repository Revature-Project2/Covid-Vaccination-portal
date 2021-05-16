import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from './appointment';
import { User } from './user';
@Injectable({
  providedIn: 'root'
})
export class CancelbookingService {
  // private urlBase = "http://localhost:9096/admin/pass";
  // private httpCli : HttpClient;  
  // constructor(httpCli: HttpClient) { 
  //   this.httpCli = httpCli;
  // }


  baseurl = 'http://localhost:9010/users/cancelbooking';
  deleteUrl = 'http://localhost:9010/users';
  private httpCli : HttpClient;  
  constructor(httpCli: HttpClient) { 
    this.httpCli = httpCli;
  }  

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*'     
    })
  }
  // const httpHead={
  //   headers: new HttpHeaders({
  //     'Content-Type': 'application/json',
  //     'Access-Control-Allow-Origin':'*'
  //   })
  // };

  GetAppointments(user): Observable<Appointment>{  
   const httpHead={
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*'
    })
  };
    // return this.httpCli.post<Admin>(this.urlBase, admin, httpHead);
    return this.httpCli.post<Appointment>(this.baseurl, user, httpHead);
  }

  DeleteAppointment(userId): Observable<User>{
    const httpHead1={
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Response-Type': 'application/json',
        'Access-Control-Allow-Origin':'*'
      })
    };
   
    return this.httpCli.post<User>(this.deleteUrl+'/'+ userId, httpHead1);
  }
}
