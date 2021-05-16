import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AuthGaurdService } from './auth-gaurd.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('AuthGaurdService', () => {
  let service: AuthGaurdService;

  beforeEach(() => {
    TestBed.configureTestingModule({

      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers:[
        AuthGaurdService
      ]
    });
    service = TestBed.inject(AuthGaurdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
