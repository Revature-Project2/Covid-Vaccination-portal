import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ClinicService } from '../clinic.service';
import { Clinic } from './clinic';

@Component({
  selector: 'app-manage-clinics',
  templateUrl: './manage-clinics.component.html',
  styleUrls: ['./manage-clinics.component.scss']
})
export class ManageClinicsComponent implements OnInit {

  selectedItem:string;
  openTime : any="Opening Time";
  closeTime :any= "Closing Time";
  beds:number;
  startDate:Date;
  endDate:Date;
  clinicName:string;
  abc:Time;
 isVisible;
 isUpdated;
   dateRange = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });
  
  private selected = 'option2';

  clinicGroup=new FormGroup({
   
    clinicName: new FormControl(''),
    openingTime: new FormControl(''),
   closingTime: new FormControl(''),
   numberOfBeds: new FormControl('')
    
    });
 

  constructor(private clinicService: ClinicService, private httpCli: HttpClient) { }

  public urlBase :any= "http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic";



  clinicList:Clinic[];
  ngOnInit(): void {
    
  //   console.log("before loading");
  //  this.clinicService.getAllClinics().subscribe(
  //   response =>{
  //    // console.log(response);
  //     this.clinicList=response;
  //     console.log("loaded");
  //    }
  // );
  
  (async()=>{
    const rawResponse = await fetch("http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic", {method: 'GET', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow.Origin': '*'
    }
    });
    const clinics = await rawResponse.json();
    this.clinicList=clinics;
    // console.log(this.clinicList);
    // console.log(this.clinicList);
    // setTimeout(function(){console.log(clinics);},1000)
    // console.log(clinics);
  })()

  }

  public submitClinic(clinic: FormGroup)
  {
    console.log("");
    //console.log(clinic.date);

    
  }


  public onClinicSelected(event)
  {
    const value = event.target.value;
   this.selectedItem = value;
   //console.log("selected value in clinic dropdown"+value);

   for(let clinic of this.clinicList)
   {
     if(clinic.clinicName == this.selectedItem)
     {
       this.clinicName=clinic.clinicName
       this.openTime=clinic.openingTime;
       this.closeTime=clinic.closingTime;
       this.beds=clinic.numberOfBeds;

       this.isVisible=true;
     }
   //  console.log(clinic.openingTime);
     //console.log("Clisngb Time are "+ this.closeTime);
     //console.log("Beds are"+ this.beds);
     this.isUpdated=false;


   }
  }

  updateOT(temp:any):void{
    this.openTime=temp.target.value;
  }
  updateCT(temp:any):void{
    this.closeTime=temp.target.value;
  }
  updateB(temp:any):void{
    this.beds=temp.target.value;
  }
  firstD:any;
  secondD:any;
  firstDate(temp:any):void{
    let year:any= Number(temp.target.value.substring(0,4));
    let month:any= (Number(temp.target.value.substring(5,7)))-1;
    let day:any= Number(temp.target.value.substring(8,10));
    this.firstD= new Date(year,month,day).getTime();
  }
  secondDate(temp:any):void{
    let year:any= Number(temp.target.value.substring(0,4));
    let month:any= (Number(temp.target.value.substring(5,7)))-1;
    let day:any= Number(temp.target.value.substring(8,10));
    this.secondD= new Date(year,month,day).getTime();
  }
  onDatesClick(temp:any):void{
    (async()=>{
      const rawResponse = await fetch(
        `http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/timeslots/opendates/?date1=${this.firstD}&date2=${this.secondD}&clinic=${this.clinicName}`,
       {method: 'PUT', headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:4200'
        // 'Access-Control-Allow-Methods': 'POST',
        // 'Access-Control-Allow-Headers': 'Content-Type'
      }
      });
      const clinic2 = await rawResponse.json();
      this.isUpdated=true;

    })()    
   
  }


  public onUpdateClick(clinic : FormGroup){
    

      (async()=>{
        const rawResponse = await fetch(
          `http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic/updateclinic?clinicName=${this.clinicName}&openTime=${this.openTime}&closeTime=${this.closeTime}&beds=${this.beds}`,
         {method: 'PATCH', headers:{
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': 'http://localhost:4200'
          // 'Access-Control-Allow-Methods': 'POST',
          // 'Access-Control-Allow-Headers': 'Content-Type'
        }
        });
        const clinic2 = await rawResponse.json();
        this.isUpdated=true;
        console.log(clinic2);
      })()    
     
    
  // (async()=>{
  //   const config = {
  //     method: 'POST',
  //     headers: {
  //         'Accept': 'application/json',
  //         'Content-Type': 'application/json',
  //         'Access-Control-Allow-Origin': 'http://localhost:4200'
  //     },
  //     body: JSON.stringify(clinic.value)
  // }
  //           const rawResponse = await fetch("http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic/updateclinic", config);
  //         })() 
               
    //          {method: 'POST', headers:{
    //           'Accept': 'application/json',
    //           'Content-Type': 'application/json',
    //           'Access-Control-Allow-Origin': 'http://localhost:4200'
    //           // 'Access-Control-Allow-Methods': 'POST',
    //           // 'Access-Control-Allow-Headers': 'Content-Type'
    //         }
    //         });
    //         // const clinics = await rawResponse.json();
    //         // this.clinicList=clinics;
    //         // console.log(clinics);
    //       })()   
    
     
     
      // this.clinicService.updateClinic(stringClinic).subscribe(
      //   response=>{
      //     this.isUpdated=true;        
      //       for(let clinic of this.clinicList)
      //       {
      //         if(clinic.clinicName == response.clinicName)
      //         {
      //           clinic.openingTime=response.openingTime;
      //           clinic.closingTime=response.closingTime;
      //           clinic.numberOfBeds=response.numberOfBeds;    
      //         }
      //       }
      //   }
      // )
  }
}

