import { ChangeDetectorRef,ChangeDetectionStrategy, ViewEncapsulation } from '@angular/core';
import { Component,NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import {   CalendarViewPeriod,
  CalendarMonthViewBeforeRenderEvent,
  CalendarWeekViewBeforeRenderEvent,
  CalendarDayViewBeforeRenderEvent, CalendarMonthViewDay, CalendarEvent, CalendarView ,DateAdapter} from 'angular-calendar';
import { startOfDay, endOfDay, addMonths } from 'date-fns';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { WeekViewHour, WeekViewHourColumn } from 'calendar-utils';

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

  title:Date = new Date();

  selectedMonthViewDay: CalendarMonthViewDay;
  selectedDayViewDate: Date;
  hourColumns: WeekViewHourColumn[];
  selectedDays: any = [];



  setView(view: CalendarView) {
    this.view = view;
  }

  clickeddDate: Date;

  clickedColumn: number;

  startHour:number=8;
  closeHour:number=20;
  
  // dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
  //   this.viewDate=date;
  //   this.clickeddDate=date;
  //   // this.setView(CalendarView.Week);
  //   console.log(date);
  //   console.log(events);
  //   //this.openAppointmentList(date)
  // }

  dayClicked(day: CalendarMonthViewDay): void {
    console.log(day);
    // this.setView(CalendarView.Week);
    console.log(day.events);
    this.selectedMonthViewDay = day;
    const selectedDateTime = this.selectedMonthViewDay.date.getTime();
    const dateIndex = this.selectedDays.findIndex(
      (selectedDay) => selectedDay.date.getTime() === selectedDateTime
    );
    if (dateIndex > -1) {
      delete this.selectedMonthViewDay.cssClass;
      this.selectedDays.splice(dateIndex, 1);
    } else {
      this.selectedDays.push(this.selectedMonthViewDay);
      day.cssClass = 'cal-day-selected';
      this.selectedMonthViewDay = day;
    }
  }

  beforeMonthViewRender({ body }: { body: CalendarMonthViewDay[] }): void {
    body.forEach((day) => {
      if (
        this.selectedDays.some(
          (selectedDay) => selectedDay.date.getTime() === day.date.getTime()
        )
      ) {
        day.cssClass = 'cal-day-selected';
      }
    });
  }

  hourSegmentClicked(date: Date) {
    this.selectedDayViewDate = date;
    this.addSelectedDayViewClass();
  }
  beforeWeekOrDayViewRender(event: CalendarWeekViewBeforeRenderEvent) {
    this.hourColumns = event.hourColumns;
    this.addSelectedDayViewClass();
  }

  private addSelectedDayViewClass() {
    this.hourColumns.forEach((column) => {
      column.hours.forEach((hourSegment) => {
        hourSegment.segments.forEach((segment) => {
          delete segment.cssClass;
          if (
            this.selectedDayViewDate &&
            segment.date.getTime() === this.selectedDayViewDate.getTime()
          ) {
            segment.cssClass = 'cal-day-selected';
          }
        });
      });
    });
  }


  myMethod(event:any):void{
    console.log(event);
    this.clickeddDate=event.date;
  }


  // hourClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
  hourClicked(): void {
    console.log("hi");
    // console.log(date);
  }
  secClick(){
    console.log("hii");
    // console.log(events)
  }
  events: CalendarEvent[] = [
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
    // {
    //   start: startOfDay(new Date()),
    //   title: 'Second event',
    // }
  ]
  

  
}



dateAdapter : DateAdapter;
  console.log(DateAdapter);
