import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';

import { Policy } from './policy.model';
import { post } from 'selenium-webdriver/http';

@Injectable({
  providedIn: 'root'
} )
export class ServiceService {

  constructor( private firestore: AngularFirestore ) { }
  getPolicies() {
    return this.firestore.collection('policies').snapshotChanges();
}
  
  createPolicy(policy: Policy){
    const newId = this.firestore.createId();
    this.firestore.collection('policies').doc(newId).set({
    id: "1",
    name: "mona",
    surname: "borisagar"
})
    return this.firestore.collection('policies').add(policy);
}
updatePolicy(policy: Policy){
  delete policy.id;
  this.firestore.doc('/policies/EFtNQ28j9PhcZ5ZZ9g5R' + policy.id).update(policy);
}
deletePolicy(policyid){
  alert(policyid);
   
  this.firestore.collection('policies/').ref.where("id", "==", "1");
 //this.firestore.doc('policies/'+"UP4daCsFRyuA4iSgQkd2").delete();
 
}


}