import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Clinic } from './manage-clinics/clinic';
@Injectable({
  providedIn: 'root'
})
export class ClinicService {

  // private urlBase= "http://localhost:9010/clinic";
  private urlBase= "http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic";
  constructor(private httpCli: HttpClient ) { }
  public getAllClinics():Observable<Clinic[]>{
    const httpHead={
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow.Origin': '*'
      }),
    };
    return this.httpCli.get<Clinic[]>(this.urlBase, httpHead);
  }
  public updateClinic(clinic):Observable<Clinic>{
   // console.log("in clinic service"+clinic);
    const httpHead={

      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow.Origin': '*'
      }),
     

    };
    return this.httpCli.post<Clinic>(this.urlBase+"/updateclinic",clinic, httpHead);
  }


}
