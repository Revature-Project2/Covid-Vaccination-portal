import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ClinicService } from '../clinic.service';

import { CalendarAdminComponent } from './calendar-admin.component';

describe('CalendarAdminComponent', () => {
  let component: CalendarAdminComponent;
  let fixture: ComponentFixture<CalendarAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarAdminComponent ],
      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers:[
        ClinicService
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
