import { TestBed } from '@angular/core/testing';

import { FirebaseService } from './firebase.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { RouterTestingModule } from '@angular/router/testing';
describe('FirebaseService', () => {
  beforeEach(() => TestBed.configureTestingModule({

    imports:[
      HttpClientTestingModule,
      RouterTestingModule
    ],
    providers:[
      FirebaseService
    ]
  }));

  // it('should be created', () => {
  //   const service: FirebaseService = TestBed.get(FirebaseService);
  //   expect(service).toBeTruthy();
  // });
});
