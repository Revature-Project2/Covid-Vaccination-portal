import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { formdata } from '../contact/contact';

@Injectable({
  providedIn: 'root'
})
export class CovidserviceService {
   private baseurl="http://localhost:9096/users/saveuserandappointment"
  constructor(private httpCli:HttpClient) { }


  public postForm(form): Observable<formdata>{
    const httpHead={
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*'
      })
    };
    return this.httpCli.post<formdata>(this.baseurl, form, httpHead);
  }
}
