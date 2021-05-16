import { TestBed } from '@angular/core/testing';

import { CancelbookingService } from './cancelbooking.service';

describe('CancelbookingService', () => {
  let service: CancelbookingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CancelbookingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
