import { Time } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ClinicService } from '../clinic.service';
import { Clinic } from './clinic';
import { ChangeDetectorRef,ChangeDetectionStrategy, ViewEncapsulation, Output, Input } from '@angular/core';
import { Component,NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import {   CalendarViewPeriod,
  CalendarMonthViewBeforeRenderEvent,
  CalendarWeekViewBeforeRenderEvent,
  CalendarDayViewBeforeRenderEvent, CalendarMonthViewDay, CalendarEvent, CalendarView ,DateAdapter} from 'angular-calendar';
import { startOfDay, endOfDay, addMonths } from 'date-fns';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { WeekViewHour, WeekViewHourColumn } from 'calendar-utils';
import { ngfactoryFilePath } from '@angular/compiler/src/aot/util';
import * as EventEmitter from 'events';

@Component({
  selector: 'app-calendar-admin',
  templateUrl: './calendar-admin.component.html',
  styleUrls: ['./calendar-admin.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class CalendarAdminComponent implements OnInit {




  viewDate: Date = new Date();
  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;

  // title:any = new Date();
  
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
    // this.selectedMonthViewDay = day;
    // const selectedDateTime = this.selectedMonthViewDay.date.getTime();
    // const dateIndex = this.selectedDays.findIndex(
    //   (selectedDay) => selectedDay.date.getTime() === selectedDateTime
    // );
    // if (dateIndex > -1) {
    //   delete this.selectedMonthViewDay.cssClass;
    //   this.selectedDays.splice(dateIndex, 1);
    // } else {
    //   this.selectedDays.push(this.selectedMonthViewDay);
    //   day.cssClass = 'cal-day-selected';
    //   this.selectedMonthViewDay = day;
    // }
  }
  weekHourClicked(day :any){
    this.title=day.date;
    this.clickeddDate= day.date;
    this.selectedDayViewDate = day.date;
    this.availableTimes.forEach((openPeriod)=>{if(day.date.getTime()>=openPeriod[0] && day.date.getTime()<=openPeriod[1] 
                                              && this.fullSegments.indexOf(day.date.getTime())==-1){
      this.bookingTime=day.date;}else{
        this.bookingTime=null;
      }
    });
    // this.addSelectedDayViewClass();
    // this.addSelectedDayViewClass();
  }

  // @Input() selectedItem:string;
  // @Input() clinicList:Clinic[];

  // ngOnInt(selectedItem){
  // }
ngOnChanges(){
  (async()=>{
    const rawResponse = await fetch("http://localhost:9010/clinic", {method: 'GET', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow.Origin': '*'
    }
    });
    const clinics = await rawResponse.json();
    this.clinicList=clinics;
    // setTimeout(function(){console.log(clinics);},1000)
  })()
}




  beforeMonthViewRender({body,header,period}): void {


    // (async()=>{
    //   const rawResponse = await fetch("http://localhost:9010/clinic", {method: 'GET', headers:{
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json',
    //     'Access-Control-Allow.Origin': '*'
    //   }
    //   });
    //   const clinics = await rawResponse.json();
    //   this.clinicList=clinics;
    //   // setTimeout(function(){console.log(clinics);},1000)
    // })()

    body.forEach((day) => {
      // day.cssClass= 'unavailable-day';
      if(day.isPast){ day.cssClass= 'unavailable-day';}
      // this.availableTimes.forEach((openPeriod)=>{if((day.date.getTime()>=openPeriod[0] && day.date.getTime()<=openPeriod[1])
      //                                               ||(day.date.getTime()>=openPeriod[0] && day.date.getTime()<openPeriod[0]+86400000)
      //                                               ||(day.date.getTime()<=openPeriod[1] && day.date.getTime()>openPeriod[1]-86400000)
      this.availableTimes.forEach((openPeriod)=>{if((openPeriod[0]-day.date.getTime()<86400000 && openPeriod[0]-day.date.getTime()>0
                                                  
        
        )
        ){
        day.cssClass= 'available-day';}});
      // if(day.date.getMonth() && day.date.getDate()){day.cssClass= 'available-day'}
      if(day.events.length>this.numberOfBeds*(this.closeHour-this.startHour)){day.cssClass= 'full-day';}
      if (
        this.selectedDays.some(
          (selectedDay) => selectedDay.date.getTime() === day.date.getTime()
        )
      ) {
        day.cssClass = 'cal-day-selected';
      }
    });

    //  this.clinicService.getAllClinics().subscribe(
    //   response =>{
    //     this.clinicList=response;
    //    }
    // );
    
   


  }
  beforeWeekOrDayViewRender(event: CalendarWeekViewBeforeRenderEvent) {
    this.hourColumns = event.hourColumns;
    // this.addSelectedDayViewClass();
    this.title=event.period.start.getDay();
    event.header.forEach((day)=>{
      if(day.isPast){ day.cssClass= 'unavailable-day';}
    });
    event.hourColumns.forEach((hourColumn)=>{
      // this.availableTimes.forEach((openPeriod)=>{
      //   if(hourColumn.date.getTime()>=openPeriod[0] && hourColumn.date.getTime()<=openPeriod[1]){
      //   hourColumn.hours.forEach((hour)=>{hour.segments.forEach((segment)=>{segment.cssClass = 'available-day';})});
      // }
        hourColumn.hours.forEach((hour)=>{hour.segments.forEach((segment)=>{
          this.availableTimes.forEach((openPeriod)=>{
            if(segment.date.getTime()>=openPeriod[0] && segment.date.getTime()<=openPeriod[1]){ 
              segment.cssClass = 'available-day';}
              if(segment.date.getTime()<new Date().getTime()){
                segment.cssClass = 'unavailable-day';
              }
          })});
      }
      //   if(hourColumn.date.getTime()<new Date().getTime()){
      //   hourColumn.hours.forEach((hour)=>{hour.segments.forEach((segment)=>{segment.cssClass = 'unavailable-day';})});
      // }
    )
    hourColumn.hours.forEach((hour)=>{hour.segments.forEach((segment)=>{
      let segmentEvents=0;
      this.availableTimes.forEach((openPeriod)=>{
        this.events.forEach((event)=>{if(new Date(event.start).getTime()+200000>new Date(segment.date).getTime()
                                              &&new Date(event.start).getTime()-200000<new Date(segment.date).getTime() ){
        // hourColumn.events.forEach((event)=>{if(new Date(event.event.start).getTime()+200000>new Date(segment.date).getTime()
        //                                       &&new Date(event.event.start).getTime()-200000<new Date(segment.date).getTime() ){
          segmentEvents++;
        }});   
      });
      if(segmentEvents>=this.numberOfBeds){
        segment.cssClass='full-day';
        this.fullSegments.push(segment.date.getTime());
      }});});
    });
  }
  // beforeMonthViewRender({ body }: { body: CalendarMonthViewDay[] }): void {
  //   this.fullDay.cssClass= 'fullDays';
  //   body.forEach((day) => {
  //     if (
  //       this.selectedDays.some(
  //         (selectedDay) => selectedDay.date.getTime() === day.date.getTime()
  //       )
  //     ) {
  //       day.cssClass = 'cal-day-selected';
  //     }
  //   });
  // }
  // dayy:any=null;

  // @Output() mouseH = new EventEmitter<string>();
  //   mouseHovered(day){
  //     this.mouseH.emit(`${day.path[1].firstElementChild.ariaLabel}`);
  //   }

  // private addSelectedDayViewClass() {
  //   this.hourColumns.forEach((column) => {
  //     column.hours.forEach((hourSegment) => {
  //       hourSegment.segments.forEach((segment) => {
  //         delete segment.cssClass;
  //         if (
  //           this.selectedDayViewDate &&
  //           segment.date.getTime() === this.selectedDayViewDate.getTime()
  //         ) {
  //           segment.cssClass = 'cal-day-selected';
  //         }
  //       });
  //     });
  //   });
  // }

 

 
  events: CalendarEvent[] = [
    // {
    //   start: startOfDay(new Date()),
    //   title: 'Second event',
    // }
  ];














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

  public urlBase :any= "http://localhost:9010/clinic";



  clinicList:Clinic[];
  ngOnInit(): void {

  //  this.clinicService.getAllClinics().subscribe(
  //   response =>{
  //     this.clinicList=response;
  //    }
  // );
  
  (async()=>{
    const rawResponse = await fetch("http://localhost:9010/clinic", {method: 'GET', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow.Origin': '*'
    }
    });
    const clinics = await rawResponse.json();
    this.clinicList=clinics;

  })()

  }

  public submitClinic(clinic: FormGroup)
  {
  }


  public onClinicSelected(event)
  {

    const value = event.target.value;
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
    const rawResponse = await fetch(`http://localhost:9010/timeslots/appointments?clinic=${value}`, {method: 'GET', headers:{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow.Origin': '*'
    }
    });
    const appointments = await rawResponse.json();

    appointments.forEach((appt)=>{
      let js={start:appt, title:""};
      this.events.push(js);
    })
  })();

   (async()=>{
    const rawResponse = await fetch(`http://localhost:9010/timeslots/dates?status=true&clinic=${value}`, {method: 'GET', headers:{
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
    // setTimeout(function(){console.log(clinics);},1000)

  })();
   






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
        `http://localhost:9010/timeslots/opendates/?date1=${this.firstD}&date2=${this.secondD}&clinic=${this.clinicName}`,
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
      this.conf=true;
    })()    
   
  }
  titled:any=this.title.toString().substring(0,15);



  conf:any="";
cond:any=false;
  public onUpdateClick(clinic : FormGroup){
    

      (async()=>{
        const rawResponse = await fetch(
          `http://localhost:9010/clinic/updateclinic?clinicName=${this.clinicName}&openTime=${this.openTime}&closeTime=${this.closeTime}&beds=${this.beds}`,
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
        this.conf=true;
        this.cond=true;
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
  //           const rawResponse = await fetch("http://localhost:9010/clinic/updateclinic", config);
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

  
