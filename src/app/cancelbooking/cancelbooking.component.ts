import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Appointment } from '../services/appointment';
import { CancelbookingService } from '../services/cancelbooking.service';
import { PipeTransform, Pipe } from '@angular/core';
import { User } from '../services/user';


@Component({
  selector: 'app-cancelbooking',
  templateUrl: './cancelbooking.component.html',
  styleUrls: ['./cancelbooking.component.scss']
})
export class CancelbookingComponent implements OnInit {
 clinicName; date; time; date1; time1; dateTime; userId; displaySuccess;firs;las;ddate;ttime;

private user : User;

  isLinear = false;
  ageGroup:string;
  panelOpenState = false;
  //radio button
  private defaultSelected = 0;
  public selection: number =0;
  RadioButtonList:any;

  // firstFormGroup = new FormGroup({
  //   confirmationCodeCtrl : new FormControl(''),
  //   emailCtrl :  new FormControl('')
  // });
  firstFormGroup : FormGroup;

  secondFormGroup: FormGroup;

  appointmentList : Appointment[];
  constructor(private _formBuilder: FormBuilder, private cancelBookingService: CancelbookingService) {}
  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      //firstCtrl: ['', Validators.required]
     // options: new FormControl((this.selection))
     confirmationCodeCtrl: [''],
     emailCtrl:[''] 
     // emailCtrl:['', Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]
    });
  }
  get f() { return this.firstFormGroup.controls; }
  validation_messages = {
    'confirmationcode': [
      { type: 'required', message: 'confirmation code  is required.' },
      { type: 'pattern', message: 'confirmation code pattern should be s3gYIkQXSi' },
    ],
    'email': [
      { type: 'required', message: 'email  is required.' },
      { type: 'pattern', message: 'email pattern should be youremail@fakemail.com' },
    ]
  };
submitForm(appointment : FormGroup){
  
    let stringAppointment = JSON.stringify(appointment.value); 

    this.cancelBookingService.GetAppointments(stringAppointment).subscribe(
      data =>{ 
             
        this.clinicName =data[0].clinic.clinicName;
        this.dateTime = data[0].timeslot.dateTime;
        // this.dateTime = new Date(this.dateTime).toISOString().slice(0, 19).replace('T', ' ');
        this.date = new Date(this.dateTime).toISOString().slice(0, 10).replace('T', ' ');
        this.time = new Date(this.dateTime).toISOString().slice(11, 19).replace('T', ' ');
        this.dateTime = data[1].timeslot.dateTime;
        this.date1 = new Date(this.dateTime).toISOString().slice(0, 10).replace('T', ' ');
        this.time1 = new Date(this.dateTime).toISOString().slice(11, 19).replace('T', ' ');
        this.userId = data[0].user.userId;
        this.user = new User(data[0].user.userId);
      }
    )
}
conf:any;
email:any;
myConf(c:any){
 this.conf=c.target.value;
}
myEmail(e:any){
  this.email= e.target.value;
}
mySub(){
  (async()=>{
    const rawResponse = await fetch(`http://localhost:9010/appointments/precancel?email=${this.email}&conf=${this.conf}`,
     {method: 'POST', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
      // 'Access-Control-Allow-Methods': 'POST',
      // 'Access-Control-Allow-Headers': 'Content-Type'
    }
    });
    const success = await rawResponse.json();
    this.firs=success.firstName;
    this.las=success.lastName;
  })()  
}


 submitForm1(){  
  (async()=>{
    const rawResponse = await fetch(`http://localhost:9010/appointments/cancel?email=${this.email}&conf=${this.conf}`,
     {method: 'POST', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
      // 'Access-Control-Allow-Methods': 'POST',
      // 'Access-Control-Allow-Headers': 'Content-Type'
    }
    });
    const success = await rawResponse.json();
    this.displaySuccess="Appointment successfully cancelled. Thank you"
  })() 

 };
}
