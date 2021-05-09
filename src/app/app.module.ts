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
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PolicyListComponent } from './policy-list/policy-list.component';
import { SliderComponent } from './slider/slider.component';
import { PaymentComponent } from './payment/payment.component';
import { ChatboxComponent } from './chatbox/chatbox.component';
import { ChatService } from './chat.service';
<<<<<<< HEAD

import { CalendarScheduleComponent } from './calendar-schedule/calendar-schedule.component';
import { ContactComponent } from './contact/contact.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
=======
import { ReactiveFormsModule } from '@angular/forms';
import {MatExpansionModule} from '@angular/material/expansion';



import { ContactComponent } from './contact/contact.component';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { ContactusformComponent } from './contactusform/contactusform.component';
import { TestvalidationComponent } from './testvalidation/testvalidation.component';
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutUsComponent,
    ContactComponent,
    PriceListComponent,
    SpecialComponent,
    ServicesComponent,
    TestimonialsComponent,
    HeaderComponent,
    FooterComponent,
    SliderComponent,
    PolicyListComponent,
    PaymentComponent,
    ChatboxComponent,
<<<<<<< HEAD
    CalendarScheduleComponent
=======
    ContactusformComponent,
    TestvalidationComponent
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    FormsModule,
    AppRoutingModule,
<<<<<<< HEAD
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    })
=======
    ReactiveFormsModule,
    MatCarouselModule,
    MatExpansionModule,
    
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf
  ],
  providers: [ChatService],
  bootstrap: [AppComponent]
})
export class AppModule { }
