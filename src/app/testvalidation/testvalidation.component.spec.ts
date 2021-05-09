import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestvalidationComponent } from './testvalidation.component';

describe('TestvalidationComponent', () => {
  let component: TestvalidationComponent;
  let fixture: ComponentFixture<TestvalidationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestvalidationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TestvalidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
