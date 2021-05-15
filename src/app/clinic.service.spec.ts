import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ClinicService } from './clinic.service';

describe('ClinicService', () => {
  let service: ClinicService;

  beforeEach(() => {
    TestBed.configureTestingModule({

      imports:[
        HttpClientTestingModule
      ],
      providers:[
        ClinicService
      ]

    });
    service = TestBed.inject(ClinicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
