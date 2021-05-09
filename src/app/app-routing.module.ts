import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
<<<<<<< HEAD

const routes: Routes = [];
=======
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { ContactusformComponent } from './contactusform/contactusform.component';
import { PriceListComponent } from './price-list/price-list.component';
import { ServicesComponent } from './services/services.component';
import { SliderComponent } from './slider/slider.component';
import { SpecialComponent } from './special/special.component';
import { TestimonialsComponent } from './testimonials/testimonials.component';
import { TestvalidationComponent } from './testvalidation/testvalidation.component';

const routes: Routes = [
{path:"aboutus",component:AboutUsComponent},
{path:"price",component:PriceListComponent},
{path:"special",component:SpecialComponent},
{path:"service",component:ServicesComponent},
{path:"testimonial",component:TestimonialsComponent},
{path:"bookappointment",component:ContactComponent},
{path:"contactusform",component:ContactusformComponent},
{path:"home",component:SliderComponent},
{path:"test",component:TestvalidationComponent},
{path:"**",component:SliderComponent},
];
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
