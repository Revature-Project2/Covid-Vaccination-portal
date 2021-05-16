import { TestBed } from '@angular/core/testing';
import { CovidserviceService } from './covidservice.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClient, HttpHeaders } from '@angular/common/http';

describe('CovidserviceService', () => {
  let service: CovidserviceService;
  let httpTestingController: HttpTestingController;
  let httpClient: HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({

      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ],
      providers:[
        CovidserviceService
      ]
    });
    service = TestBed.inject(CovidserviceService);
    httpTestingController = TestBed.inject(HttpTestingController);
    httpClient = TestBed.get(HttpClient);
  });
  afterEach(() => {
    httpTestingController.verify();
  });

  const baseurl="http://localhost:9096/users/saveuserandappointment";
  it('can test HttpClient.get', () => {
    const formdata:any={
      address: "44, Pinecone Dr,Etobicoke, ON",
      bookdatectrl: "2021-05-25T04:00:00.000Z",
      clinicId: "3",
      dateOfBirth: "2021-05-21T04:00:00.000Z",
      email: "nikunj.mehta2062@gmail.com",
      firstName: "Mona",
      healthCardNumber: "3243243233",
      lastName: "Borisagar",
      options: "0",
      phoneNumber: "454354",
      postalcode: "M9W 7B4",
      timeslotId: "0",
      vaccineId: "1",
  
    }
    const httpHead={
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*'
      })
    };

    // Make an HTTP GET request
    httpClient.post<any>(baseurl,formdata,httpHead)
      .subscribe(data =>
        // When observable resolves, result should match test data
        expect(data).toEqual(formdata)
      );
      const req = httpTestingController.expectOne(baseurl);
    expect(req.request.method).toEqual('POST');
    req.flush(formdata);

    });
      
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  // it('check uri of method', () => {
  //   const formdata={
  //   address: "44, Pinecone Dr,Etobicoke, ON",
  //   bookdatectrl: "2021-05-25T04:00:00.000Z",
  //   clinicId: "3",
  //   dateOfBirth: "2021-05-21T04:00:00.000Z",
  //   email: "nikunj.mehta2062@gmail.com",
  //   firstName: "Mona",
  //   healthCardNumber: "3243243233",
  //   lastName: "Borisagar",
  //   options: "0",
  //   phoneNumber: "454354",
  //   postalcode: "M9W 7B4",
  //   timeslotId: "0",
  //   vaccineId: "1",

  // }
  // const req = httpTestingController.expectOne('http://localhost:9096/users/saveuserandappointment');
  // //expect(req.request.method).toEqual('POST');
  // service.postForm(formdata)
  //     .subscribe(courseData => {
  //       expect(courseData.firstName).toEqual('Mona');
  //     });

  
  // });
});
