import { Component, OnInit } from '@angular/core';
import { FormArray, FormsModule } from "@angular/forms";
import {ServiceService} from '../service.service';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';


import { Router } from '@angular/router';
import { FirebaseService } from '../services/firebase.service';
import { CovidserviceService } from '../services/covidservice.service';
import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef,ChangeDetectionStrategy, ViewEncapsulation, Output, Input } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import {   CalendarViewPeriod,
  CalendarMonthViewBeforeRenderEvent,
  CalendarWeekViewBeforeRenderEvent,
  CalendarDayViewBeforeRenderEvent, CalendarMonthViewDay, CalendarEvent, CalendarView ,DateAdapter} from 'angular-calendar';
import { startOfDay, endOfDay, addMonths } from 'date-fns';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { WeekViewHour, WeekViewHourColumn } from 'calendar-utils';
import { ngfactoryFilePath } from '@angular/compiler/src/aot/util';
import * as EventEmitter from 'events';
import { Clinic } from '../manage-clinics/clinic';

@Component({
  selector: 'app-calendar-user',
  templateUrl: './calendar-user.component.html',
  styleUrls: ['./calendar-user.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class CalendarUserComponent implements OnInit {
 firstFFormGroup=new FormGroup({  
      clinicName: new FormControl(''),
      vaccine: new FormControl(''),
      bookinTime: new FormControl('')    
      });
  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  title:any = new Date();
  selectedMonthViewDay: CalendarMonthViewDay;
  selectedDayViewDate: Date;
  hourColumns: WeekViewHourColumn[];
  selectedDays: any = [];
  fullDays: any= [];
  fullSegments:any = [];
  fullDay:any={date: new Date('May 15, 2021 03:24:00'), day: 4, isPast: true, isToday: false, isFuture: false, cssClass: "fullDays"};
  pastDays: CalendarMonthViewDay[];
  // availableTimes=[[new Date().getTime(), new Date().getTime()+600000000]];
  availableTimes=[];
  setView(view: CalendarView) {
    this.view = view;
  }
  clickeddDate: Date;
  clickedColumn: number;
  clinic:string="north";
  numberOfBeds=1;
  startHour:number=10;
  closeHour:number=14;
  bookingTime: Date;
  dayStatus(day: CalendarMonthViewDay):string{
    if(day.isPast){ return("past date");}
  };
  MonthDayClicked(day: CalendarMonthViewDay): void {
    this.clickeddDate= day.date;
    this.title=day.date;
    this.availableTimes.forEach((openPeriod)=>{if(((day.date.getTime()>=openPeriod[0] && day.date.getTime()<=openPeriod[1])
                                              ||(day.date.getTime()<=openPeriod[0] && day.date.getTime()>=openPeriod[0]-86400000)
                                              ||(day.date.getTime()<=openPeriod[1] && day.date.getTime()>=openPeriod[1]-86400000))
                                              && !(day.events.length>this.numberOfBeds*(this.closeHour-this.startHour))
      ){
      this.viewDate=day.date;
      this.setView(CalendarView.Week);
    }});
  }
  weekHourClicked(day :any){
    this.title=day.date;
    this.clickeddDate= day.date;
    this.selectedDayViewDate = day.date;
    this.availableTimes.forEach((openPeriod)=>{if(day.date.getTime()>=openPeriod[0] && day.date.getTime()<=openPeriod[1] 
                                              && this.fullSegments.indexOf(day.date.getTime())==-1){
      this.bookingTime=day.date; this.mayVar=day.date; this.mayVar2=day.date.getTime()}else{
        this.bookingTime=null;
      }
    });
  }
  titled:any=this.title.toString().substring(0,15);
  ngOnChanges(){
    (async()=>{
      const rawResponse = await fetch("http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic", {method: 'GET', headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow.Origin': '*'
      }
      });
      const clinics = await rawResponse.json();
      this.clinicList=clinics;
    })()
  }
  beforeMonthViewRender({body,header,period}): void {
    body.forEach((day) => {
      if(day.isPast){ day.cssClass= 'unavailable-day';}
      this.availableTimes.forEach((openPeriod)=>{if((openPeriod[0]-day.date.getTime()<86400000 && openPeriod[0]-day.date.getTime()>0)
        ){
        day.cssClass= 'available-day';}});
      if(day.events.length>this.numberOfBeds*(this.closeHour-this.startHour)){day.cssClass= 'full-day';}
      if (
        this.selectedDays.some(
          (selectedDay) => selectedDay.date.getTime() === day.date.getTime()
        )
      ) {
        day.cssClass = 'cal-day-selected';
      }
    });
  }
  beforeWeekOrDayViewRender(event: CalendarWeekViewBeforeRenderEvent) {
    this.hourColumns = event.hourColumns;
    this.title=event.period.start.getDay();
    event.header.forEach((day)=>{
      if(day.isPast){ day.cssClass= 'unavailable-day';}
    });
    event.hourColumns.forEach((hourColumn)=>{
        hourColumn.hours.forEach((hour)=>{hour.segments.forEach((segment)=>{
          this.availableTimes.forEach((openPeriod)=>{
            if(segment.date.getTime()>=openPeriod[0] && segment.date.getTime()<=openPeriod[1]){ 
              segment.cssClass = 'available-day';}
              if(segment.date.getTime()<new Date().getTime()){
                segment.cssClass = 'unavailable-day';
              }
          })});
      }
    )
    hourColumn.hours.forEach((hour)=>{hour.segments.forEach((segment)=>{
      let segmentEvents=0;
      this.availableTimes.forEach((openPeriod)=>{
        this.events.forEach((event)=>{if(new Date(event.start).getTime()>=new Date(segment.date).getTime()
                                              &&new Date(event.start).getTime()<=new Date(segment.date).getTime() ){
          segmentEvents++;
        }});   
      });
      if(segmentEvents>=this.numberOfBeds){
        segment.cssClass='full-day';
        this.fullSegments.push(segment.date.getTime());
      }});});
    });
  }
  events: CalendarEvent[] = [
  ] 




  openTime : any="Opening Time";
  closeTime :any= "Closing Time";
  beds:number;
  startDate:Date;
  endDate:Date;
  clinicName:string;
  clinicList:Clinic[];
  isLinear = false;
  ageGroup:string;
  panelOpenState = false;
  cards = [];
  toggle:boolean;
  private defaultSelected = 0;
  public selection: number =0;
  public selection1: number =0;
  selectionc:number=0;
  selectionv:number=0;
  RadioButtonList:any;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  carddata;
  userForm:FormArray;
  constructor(private _formBuilder: FormBuilder,private covidserve:CovidserviceService,private httpci:HttpClient) {}
  selectedItem:any;
  nameSelected(temp:any):void{
    this.selectedItem=temp.target.value;
    const value = temp.target.value;
    this.selectedItem = value;
    this.clinicName=value;
    this.clinicList.forEach((clinic)=>{
      if (clinic.clinicName==value){
        this.openTime=clinic.openingTime;
        this.closeTime=clinic.closingTime;
        this.startHour=Number(clinic.openingTime.toString().substring(0,2));
        this.closeHour=Number(clinic.closingTime.toString().substring(0,2));
        this.beds=clinic.numberOfBeds;
        this.numberOfBeds=clinic.numberOfBeds;
      }
    });
 

    (async()=>{
      const rawResponse = await fetch(`http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/timeslots/appointments?clinic=${this.selectedItem}`, {method: 'GET', headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow.Origin': '*'
      }
      });
      const appointments = await rawResponse.json();    
      appointments.forEach((appt)=>{
        let js={start:appt, title:"", end:appt+600000};
        this.events.push(js);
      })
    })();
    (async()=>{
      const rawResponse = await fetch(`http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/timeslots/dates?status=true&clinic=${this.selectedItem}`, {method: 'GET', headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow.Origin': '*'
      }
      });
      const joinTable = await rawResponse.json();
      
      this.availableTimes=[];
      joinTable.forEach((item)=>{
        if(((new Date().getTime()>item) ||(new Date(item).getHours()<this.startHour)   || (new Date(item).getHours()>(this.closeHour)))){
        
      }else{
        this.availableTimes.push([item, item+600000])}
      }
      );
    })();
  }
  clinicList:Clinic[];
  ngOnInit() {
    (async()=>{
      const rawResponse = await fetch("http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/clinic", {method: 'GET', headers:{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow.Origin': '*'
      }
      });
      const clinics = await rawResponse.json();
      this.clinicList=clinics;
    })();
      this.firstFormGroup = this._formBuilder.group({
        options: new FormControl((this.selection)),
        firstName : ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        phoneNumber: ['', Validators.required],
        address: ['', Validators.required],
        healthCardNumber: ['', Validators.required], 
        dateOfBirth : new FormControl('', [
          Validators.required,
          ]),
          postalcode: new FormControl('', [
            Validators.required,
            ]),
            clinicId: ['', Validators.required],
            bookdatectrl: ['', Validators.required],
            timeslotId: new FormControl((this.selectionc)),
            vaccineId : new FormControl((this.selectionv))
      }      
      )      
    }
public myTest(eventss:void):void{
}
public onClinicSelected(event:void):void{ 
  }
  get f() { return this.secondFormGroup.controls; }
  validation_messages = {
    'postalcode': [
      { type: 'required', message: 'postalCode  is required.' },
     
      { type: 'pattern', message: 'Postal code pattern should be M9W 7B4' },
    ],
    'surname': [
      { type: 'required', message: 'Surname is required.' }
    ],
    'age': [
      { type: 'required', message: 'Age is required.' },
      { type: 'min', message: 'value should be between 0 to 100' },
      { type: 'max', message: 'value should be between 0 to 100' },
    ]
  };
   radioList: { id: number, name: string }[] = [
    { "id": 0, "name": "18 years or older" },
    { "id": 1, "name": "40 years or older at pharmacies and primary care providers " },
    { "id": 2, "name": "50 years or older" }
];
radioListforVaccine: { id: number, name: string }[] = [
  { "id": 0, "name": "Fizer" },
  { "id": 1, "name": "Madorna" },
  { "id": 2, "name": "Astragenica" }
];
valueChanged(event)
{
  this.toggle = true;
  this.cards = [{ id: 1, label: '8.00 a.m.'}, { id: 2, label: '9.00 a.m'}, { id: 3, label: '10.00 a.m'}];
   if(event.value == 'Wed May 26 2021 00:00:00 GMT-0400 (Eastern Daylight Time)')
   {
   }
}
public submitForm(){
  let stringFood = JSON.stringify(this.firstFormGroup.value);
  this.covidserve.postForm(stringFood).subscribe(
    response => {    
    }
  );
}
fname:any;
namefunc(temp:any):void{
  this.fname=temp.target.value;
}
lname:any;
lastfunc(temp:any):void{
  this.lname=temp.target.value;
}
email:any="";
emailfunc(temp:any):void{
  this.email=temp.target.value;
}
pnumber:any;
pnumberfunc(temp:any):void{
  this.pnumber=temp.target.value;
}
health:any;
healthfunc(temp:any):void{
  this.health=temp.target.value;
}
dob:any;
dobfunc(temp:any):void{
  this.dob=temp.target.value;
}
addr:any;
addrfunc(temp:any):void{
  this.addr=temp.target.value;
}
vaccine:any;
vaccinefunc(temp:any):any{
  this.vaccine=temp.target.value;
}
conf:any="";
cond:any=false;
mayVar:any="";
mayVar2:any=0;
myBooking(){
  (async()=>{
    const rawResponse = await fetch(
      `http://ec2-18-219-2-30.us-east-2.compute.amazonaws.com:9010/appointments/book?clinic=${this.selectedItem}&timeslot=${this.mayVar2}&vaccine=${this.vaccine}&fname=${this.fname}&lname=${this.lname}&email=${this.email}&pnumber=${this.pnumber}&health=${this.health}&dob=${this.dob}&addr=${this.addr}`, 
      {method: 'Post', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow.Origin': '*'
    }, 
    },);
    const res = await rawResponse.json();
    this.conf=`Confirmation number: ${res.confirmationNumber}`;
    this.cond=`COnfirmation Email: ${this.email}`;  
  })()
}

}
