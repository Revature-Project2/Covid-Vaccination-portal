import { CUSTOM_ELEMENTS_SCHEMA, DebugElement } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthService } from '../services/auth.service';

import { LoginComponent } from './login.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../services/admin';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ConditionalExpr } from '@angular/compiler';
import { NgModule } from '@angular/core';
import { BrowserModule, By } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';




import { MatCarouselModule } from '@ngmodule/material-carousel';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ContactComponent } from '../contact/contact.component';




describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let de:DebugElement;
  let el :HTMLElement;
  beforeEach(async () => {
   
    await TestBed.configureTestingModule({
      
        declarations: [
         
          LoginComponent,
        ],
        imports:[
          HttpClientTestingModule,
          RouterTestingModule
  
        ],
        providers:[
          AuthService
        ]
      
    })
    .compileComponents().then(()=> {

      fixture = TestBed.createComponent(LoginComponent);
      component =fixture.componentInstance;
      de = fixture.debugElement.query(By.css('form'));
      el = de.nativeElement;
    });
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it(`should have true value for  "invalidLogin" field of LoginComponent `, () => {
    expect(component.invalidLogin).toEqual("yes");
  });
  it(`In Loginpage, after  form submit , value of submitted to be true ,  `, () => {
    component.ngOnInit();
    expect(component.submitted).toEqual(true);
  });

  it(`Should call the onsubmit method`, () => {
    fixture.detectChanges;
    spyOn(component,'checkLogin');
   // .el =fixture.debugElement.query(By.css('button')).nativeElement;
  el = fixture.debugElement.query(By.css('button')).nativeElement;
   el.click()
   expect(component.checkLogin).toHaveBeenCalledTimes(1);
  });


  it(`In Loginpage, formGroup testing Validator.required  `, () => {
    component.adminCreds.controls['userName'].setValue('');
    component.adminCreds.controls['password'].setValue('');
    expect(component.adminCreds.valid).toBeFalsy();
  });
  it(`In Loginpage, form should be valid `, () => {
    component.adminCreds.controls['userName'].setValue('admin');
    component.adminCreds.controls['password'].setValue('admin');
    expect(component.adminCreds.valid).toBeTruthy();
  });
});
function checkLogin(component: LoginComponent, checkLogin: any) {
  throw new Error('Function not implemented.');
}

