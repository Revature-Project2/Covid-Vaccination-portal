import { ChangeDetectorRef,ChangeDetectionStrategy, ViewEncapsulation, Output } from '@angular/core';
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
  selector: 'calendar-schedule',
  templateUrl: './calendar-schedule.component.html',
  styleUrls: ['./calendar-schedule.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class CalendarScheduleComponent {
  
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
  availableTimes=[[new Date().getTime(), new Date().getTime()+600000000]];

  setView(view: CalendarView) {
    this.view = view;
  }
  

  clickeddDate: Date;
  clickedColumn: number;
  clinic:string="north";
  numberOfBeds=1;
  startHour:number=1;
  closeHour:number=23;
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
  beforeMonthViewRender({body,header,period}): void {
    body.forEach((day) => {
      // day.cssClass= 'unavailable-day';
      if(day.isPast){ day.cssClass= 'unavailable-day';}
      this.availableTimes.forEach((openPeriod)=>{if((day.date.getTime()>=openPeriod[0] && day.date.getTime()<=openPeriod[1])
                                                    ||(day.date.getTime()>=openPeriod[0] && day.date.getTime()<openPeriod[0]+86400000)
                                                    ||(day.date.getTime()<=openPeriod[1] && day.date.getTime()>openPeriod[1]-86400000)
        
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
  //     // console.log(day.path[1].firstElementChild.ariaLabel);
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
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+1600000),
      title: '',
      end:new Date(new Date().getTime()+2200000)
    },
    {
      start: new Date(new Date().getTime()+3600000),
      title: '',
      end:new Date(new Date().getTime()+4200000)
    },
    {
      start: new Date(new Date().getTime()+3600000),
      title: '',
      end:new Date(new Date().getTime()+4200000)
    },
    {
      start: new Date(new Date().getTime()+3600000),
      title: '',
      end:new Date(new Date().getTime()+4200000)
    },
    {
      start: new Date(new Date().getTime()+3600000),
      title: '',
      end:new Date(new Date().getTime()+4200000)
    },
    {
      start: new Date(new Date().getTime()+3600000),
      title: '',
      end:new Date(new Date().getTime()+4200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+6000000),
      title: '',
      end:new Date(new Date().getTime()+6600000)
    },
    {
      start: new Date(new Date().getTime()+5600000),
      title: '',
      end:new Date(new Date().getTime()+6200000)
    },
    {
      start: new Date(new Date().getTime()+7600000),
      title: '',
      end:new Date(new Date().getTime()+8200000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+96000000),
      title: '',
      end:new Date(new Date().getTime()+102000000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    {
      start: new Date(new Date().getTime()+300000000),
      title: '',
      end:new Date(new Date().getTime()+300600000)
    },
    // {
    //   start: startOfDay(new Date()),
    //   title: 'Second event',
    // }
  ] 
}
