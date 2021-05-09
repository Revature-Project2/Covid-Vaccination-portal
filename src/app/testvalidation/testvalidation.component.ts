import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import { FormBuilder } from '@angular/forms';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-testvalidation',
  templateUrl: './testvalidation.component.html',
  styleUrls: ['./testvalidation.component.scss']
})
export class TestvalidationComponent implements OnInit {
  firstFormGroup1: FormGroup;

  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.firstFormGroup1 = this._formBuilder.group({
      //firstCtrl: ['', Validators.required]
      firstform : new FormControl('', [
        Validators.required,
        Validators.email,
      ])
     
    });
  }
 
 submitquery(){
   console.log(this.firstFormGroup1.value);
 } 
 

  matcher = new ErrorStateMatcher();
}

