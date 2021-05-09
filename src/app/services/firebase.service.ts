import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';


@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  constructor(public db: AngularFirestore) { }

  
  createContact(value){
    alert(value.firstname);
    return this.db.collection('contact_us').add({
      firstname: value.firstname,
      lastname: value.lastname,
     
     
      
    });
  }
}
