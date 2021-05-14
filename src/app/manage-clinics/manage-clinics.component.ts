import { Time } from '@angular/common';
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
  openTime : Time;
  closeTime :Time;
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
  clinicList:Clinic[];
  private selected = 'option2';

  clinicGroup=new FormGroup({
   
    clinicName: new FormControl(''),
    openingTime: new FormControl(''),
   closingTime: new FormControl(''),
   numberOfBeds: new FormControl('')
    
    });
 

  constructor(private clinicService: ClinicService) { }

  ngOnInit(): void {
    
   this.clinicService.getAllClinics().subscribe(
    response =>{
     // console.log(response);
      this.clinicList=response;
     }
  )
  }

  public submitClinic(clinic: FormGroup)
  {
    console.log("Clicked details submitted");
    console.log(clinic);
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


  public onUpdateClick(clinic : FormGroup){
   //   console.log(clinic)

//clinic.setValue({clinicName : this.selectedItem});
      let stringClinic=JSON.stringify(clinic.value);
      
      this.clinicService.updateClinic(stringClinic).subscribe(
        response=>{

          this.isUpdated=true;        

            for(let clinic of this.clinicList)
            {
              if(clinic.clinicName == response.clinicName)
              {
                clinic.openingTime=response.openingTime;
                clinic.closingTime=response.closingTime;
                clinic.numberOfBeds=response.numberOfBeds;
              
              }
            }


        }
      )

  }


}
