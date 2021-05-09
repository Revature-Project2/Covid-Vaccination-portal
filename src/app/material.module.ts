import {NgModule} from "@angular/core";
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
<<<<<<< HEAD
=======
import { MatStepperModule } from "@angular/material/stepper";
import {MatListModule} from '@angular/material/list';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf

@NgModule({
  imports: [
  CommonModule, 
  MatToolbarModule,
  MatButtonModule, 
  MatCardModule,
  MatInputModule,
  MatDialogModule,
  MatTableModule,
  MatMenuModule,
  MatIconModule,
<<<<<<< HEAD
  MatProgressSpinnerModule
=======
  MatToolbarModule, 
  MatButtonModule,
  MatIconModule,
  MatInputModule ,
  MatStepperModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatListModule,
  MatRadioModule,
  MatDatepickerModule,
  MatNativeDateModule
  
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf
  ],
  exports: [
  CommonModule,
   MatToolbarModule, 
   MatButtonModule, 
   MatCardModule, 
   MatInputModule, 
   MatDialogModule, 
   MatTableModule, 
   MatMenuModule,
   MatIconModule,
<<<<<<< HEAD
   MatProgressSpinnerModule
=======
   MatProgressSpinnerModule,
   MatToolbarModule, 
   MatButtonModule,
   MatIconModule,
   MatInputModule ,
   MatStepperModule,
   MatInputModule,
   MatListModule,
   MatRadioModule,
   MatDatepickerModule,
   MatNativeDateModule
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf
   ],
})
export class CustomMaterialModule { }