import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ClinicService } from '../clinic.service';

import { ManageClinicsComponent } from './manage-clinics.component';

describe('ManageClinicsComponent', () => {
  let component: ManageClinicsComponent;
  let fixture: ComponentFixture<ManageClinicsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageClinicsComponent ],
     
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
    fixture = TestBed.createComponent(ManageClinicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
