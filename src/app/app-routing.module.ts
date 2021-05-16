import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AdminComponent } from './admin/admin.component';
import { ContactComponent } from './contact/contact.component';
import { ContactusformComponent } from './contactusform/contactusform.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PasswordresetComponent } from './passwordreset/passwordreset.component';
import { PriceListComponent } from './price-list/price-list.component';
import { AuthGaurdService } from './services/auth-gaurd.service';
import { ServicesComponent } from './services/services.component';
import { SliderComponent } from './slider/slider.component';
import { SpecialComponent } from './special/special.component';
import { TestimonialsComponent } from './testimonials/testimonials.component';
import { TestvalidationComponent } from './testvalidation/testvalidation.component';
import { WelcomeAdminComponent } from './welcome-admin/welcome-admin.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ManageClinicsComponent } from './manage-clinics/manage-clinics.component';
import { CancelbookingComponent } from './cancelbooking/cancelbooking.component';

const routes: Routes = [
{path:"aboutus",component:AboutUsComponent},
{path:"welcomeadmin",component:WelcomeAdminComponent},
{path:"price",component:PriceListComponent},
{path:"special",component:SpecialComponent},
{path:"service",component:ServicesComponent},
{path:"testimonial",component:TestimonialsComponent},
{path:"bookappointment",component:ContactComponent},
{path:"contactusform",component:ContactusformComponent},
{path:"home",component:SliderComponent},
{path:"test",component:TestvalidationComponent},
{path:"admin/manageclinics",component:ManageClinicsComponent},
{path:"login",component:LoginComponent},
{path:"passwordreset",component:PasswordresetComponent},
{path:"admin",component:AdminComponent ,canActivate:[AuthGaurdService]},
{path:"logout", component:LogoutComponent ,canActivate:[AuthGaurdService]}, 
{path:"manageclinics",component:ManageClinicsComponent},
{path:"cancelbooking",component:CancelbookingComponent},
{path:"**",component:SliderComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
