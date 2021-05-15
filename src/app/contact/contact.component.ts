import { Component, OnInit } from '@angular/core';
import { FormArray, FormsModule } from "@angular/forms";
import {ServiceService} from '../service.service';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';


import { Router } from '@angular/router';
import { FirebaseService } from '../services/firebase.service';
import { CovidserviceService } from '../services/covidservice.service';
import { HttpClient } from '@angular/common/http';
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
  cards = [];
  toggle:boolean;
  private defaultSelected = 0;
  public selection: number =0;
  public selection1: number =0;
  selectionc:number=0;
  selectionv:number=0;
  RadioButtonList:any;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  carddata;
  userForm:FormArray;
  constructor(private _formBuilder: FormBuilder,private covidserve:CovidserviceService,private httpci:HttpClient) {}
  
  ngOnInit() {
    
      this.firstFormGroup = this._formBuilder.group({
        //firstCtrl: ['', Validators.required]
        options: new FormControl((this.selection)),
        firstName : ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        phoneNumber: ['', Validators.required],
        address: ['', Validators.required],
        healthCardNumber: ['', Validators.required],
  
        dateOfBirth : new FormControl('', [
          Validators.required,
          ]),
          postalcode: new FormControl('', [
            Validators.required,
            ]),

            clinicId: ['', Validators.required],
            bookdatectrl: ['', Validators.required],
            //cardctrl: ['', Validators.required],
            timeslotId: new FormControl((this.selectionc)),
            vaccineId : new FormControl((this.selectionv))
      })
     
    
    
    }
  
    
    // this.firstFormGroup = this._formBuilder.group({
    //   //firstCtrl: ['', Validators.required]
    //   options: new FormControl((this.selection))
    // });
    // this.secondFormGroup = this._formBuilder.group({
    //   firstName : ['', Validators.required],
    //   lastName: ['', Validators.required],
    //   email: ['', Validators.required],
    //   phoneNumber: ['', Validators.required],
    //   address: ['', Validators.required],
    //   healthCardNumber: ['', Validators.required],

    //   dateOfBirth : new FormControl('', [
    //     Validators.required,
    //     ]),
    //     postalcode: new FormControl('', [
    //       Validators.required,
    //       ])
    //       // Validators.pattern('^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$')
    // });
    // this.thirdFormGroup = this._formBuilder.group({
    //   clinicId: ['', Validators.required],
    //   bookdatectrl: ['', Validators.required],
    //   //cardctrl: ['', Validators.required],
    //   timeslotId: new FormControl((this.selectionc)),
    //   vaccineId : new FormControl((this.selectionv))
    //  // vacinectrl: new FormControl('', [ Validators.required, ]),
      
    //     //alidators.pattern('^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$')
    // });
  
    
  


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


radioListforVaccine: { id: number, name: string }[] = [
  { "id": 0, "name": "Fizer" },
  { "id": 1, "name": "Madorna" },
  { "id": 2, "name": "Astragenica" }
];


valueChanged(event)
{
  this.toggle = true;
  this.cards = [{ id: 1, label: '8.00 a.m.'}, { id: 2, label: '9.00 a.m'}, { id: 3, label: '10.00 a.m'}];
   console.log(event.value);
   if(event.value == 'Wed May 26 2021 00:00:00 GMT-0400 (Eastern Daylight Time)')
   {
     console.log("date is selcted yuppe");
   }
}
// submitForm()
// {

//   //console.log(this.userForm.value)
//    console.log(this.firstFormGroup.value);
//   // console.log(this.secondFormGroup.value);
//   // console.log(this.thirdFormGroup.value);
// }

public submitForm(){
  let stringFood = JSON.stringify(this.firstFormGroup.value);
  this.covidserve.postForm(stringFood).subscribe(
    response => {
      console.log("this is response get from admin");
      console.log(response);
     
    }
  );
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
