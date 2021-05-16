import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import { CustomMaterialModule } from './material.module';
import { HomeComponent } from './home/home.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { PriceListComponent } from './price-list/price-list.component';
import { SpecialComponent } from './special/special.component';
import { ServicesComponent } from './services/services.component';
import { TestimonialsComponent } from './testimonials/testimonials.component';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './footer/footer.component';
import { PolicyListComponent } from './policy-list/policy-list.component';
import { SliderComponent } from './slider/slider.component';
import { PaymentComponent } from './payment/payment.component';
import { ChatboxComponent } from './chatbox/chatbox.component';
import { ChatService } from './chat.service';
import { MaterialhoverDirective } from './materialhover.directive';
import { CovidserviceService } from './services/covidservice.service';
import { CalendarScheduleComponent } from './calendar-schedule/calendar-schedule.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { ReactiveFormsModule } from '@angular/forms';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';



import { ContactComponent } from './contact/contact.component';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { ContactusformComponent } from './contactusform/contactusform.component';
import { TestvalidationComponent } from './testvalidation/testvalidation.component';
import { WelcomeAdminComponent } from './welcome-admin/welcome-admin.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
//import { HttpClientModule } from '@angular/common/http';
import { ManageClinicsComponent } from './manage-clinics/manage-clinics.component';
import { LoginComponent } from './login/login.component';
import { PasswordresetComponent } from './passwordreset/passwordreset.component';
import { AdminComponent } from './admin/admin.component';
import { LogoutComponent } from './logout/logout.component';


describe('AppComponent', () => {
  let appcomponent:AppComponent;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      
      declarations: [
        AppComponent,
        HomeComponent,
        AboutUsComponent,
        ContactComponent,
        PriceListComponent,
        SpecialComponent,
        ServicesComponent,
        TestimonialsComponent,
       
        FooterComponent,
        SliderComponent,
        PolicyListComponent,
        PaymentComponent,
        ChatboxComponent,
        CalendarScheduleComponent,
        ContactusformComponent,
        TestvalidationComponent,
        WelcomeAdminComponent,
        ManageClinicsComponent,
        LoginComponent,
        PasswordresetComponent,
        AdminComponent,
        LogoutComponent,
        TestvalidationComponent,
        MaterialhoverDirective
      ],
      imports: [
        RouterTestingModule,
        BrowserModule,
        AppRoutingModule,
        BrowserModule,
        BrowserAnimationsModule,
        CustomMaterialModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        CalendarModule.forRoot({
          provide: DateAdapter,
          useFactory: adapterFactory,
        }),
        ReactiveFormsModule,
        MatCarouselModule,
        MatExpansionModule,
        MatSelectModule,
        MatFormFieldModule,
       HttpClientModule
        
      ],
        providers: [ChatService,CovidserviceService],
      
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    }).compileComponents();
   // appcomponent = TestBed.inject(App)
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'CovidVaccinePortal'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('CovidVaccinePortal');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('CovidVaccinePortal app is running!');
  });
});
