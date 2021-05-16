import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserDynamicTestingModule } from '@angular/platform-browser-dynamic/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AppRoutingModule } from '../app-routing.module';

import { TestvalidationComponent } from './testvalidation.component';

describe('TestvalidationComponent', () => {
  let component: TestvalidationComponent;
  let fixture: ComponentFixture<TestvalidationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestvalidationComponent ],
      imports:[
        HttpClientTestingModule,
        RouterTestingModule,
        BrowserDynamicTestingModule,
        FormsModule,
        ReactiveFormsModule
      ],
      providers:[
        
      ]
          })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestvalidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
