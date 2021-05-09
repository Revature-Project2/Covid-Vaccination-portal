import { Component, OnInit } from '@angular/core';
import { FormsModule } from "@angular/forms";
import {ServiceService} from '../service.service';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';


import { Router } from '@angular/router';
import { FirebaseService } from '../services/firebase.service';
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent  implements OnInit {
  isLinear = false;
  ageGroup:string;
  panelOpenState = false;

  //radio button
  private defaultSelected = 0;
  public selection: number =0;
  RadioButtonList:any;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  constructor(private _formBuilder: FormBuilder) {}

  ngOnInit() {
    this.firstFormGroup = this._formBuilder.group({
      //firstCtrl: ['', Validators.required]
      options: new FormControl((this.selection))
    });
    this.secondFormGroup = this._formBuilder.group({
      firstnameCtrl: ['', Validators.required],
      lastnameCtrl: ['', Validators.required],
      emailCtrl: ['', Validators.required],
      phonenumberCtrl: ['', Validators.required],
      addressCtrl: ['', Validators.required],
      healthcardCtrl: ['', Validators.required],

      datePicker: new FormControl('', [
        Validators.required,
        ]),
        postalcode: new FormControl('', [
          Validators.required,
          ])
          // Validators.pattern('^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$')
    });
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

submitForm()
{
  console.log(this.firstFormGroup.value);
  console.log(this.secondFormGroup.value);
}
  /* exampleForm: FormGroup;
  
    
  constructor(
    private fb: FormBuilder,
  
    private router: Router,
    public firebaseService: FirebaseService
  ) { }
   

 
  validation_messages = {
    'name': [
      { type: 'required', message: 'this is required.' },
     
      { type: 'pattern', message: 'numbers are not allowed' },
 
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

  ngOnInit() {
    this.createForm();
  }
  
  createForm() {
    this.exampleForm = this.fb.group({
      firstname: ['', [Validators.required,
        Validators.pattern("^[a-z]*$")]],
      lastname: ['', [Validators.required,
        Validators.pattern("^[a-z]*$")]],
      email: ['', [Validators.required,
        Validators.pattern("^[a-z]*$")]],
      subject: ['', [Validators.required,
        Validators.pattern("^[a-z]*$")]],
      message: ['', [Validators.required,
        Validators.pattern("^[a-z]*$")]],
     
    });
  }

 

    

  resetFields(){
    this.createForm();
  }

  onSubmit(value){
    this.firebaseService.createContact(value)
    .then(
      res => {
        this.resetFields();
        this.router.navigate(['/home']);
      }
    )
  } */


}
