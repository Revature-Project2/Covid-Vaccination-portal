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
export class ContactComponent implements OnInit {
  
  exampleForm: FormGroup;

    
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
  }


}
