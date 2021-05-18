import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { CovidserviceService } from '../services/covidservice.service';

import { CalendarUserComponent } from './calendar-user.component';

describe('CalendarUserComponent', () => {
  let component: CalendarUserComponent;
  let fixture: ComponentFixture<CalendarUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarUserComponent ],
      imports:[
        HttpClientTestingModule,
        RouterTestingModule,
        FormBuilder,
      ],
      providers:[
        CovidserviceService
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
