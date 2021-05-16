import { Time } from "@angular/common";
// constructor(public foodName:string, public calories:number, public foodId?: number){
// }

export class Appointment{
   constructor( public appointmentId : Number,
                public shot_number : Number,
                public clinic?: Object,
                public user?: Object,
                public timeslot?: Object,
                public appointmentStatus?: Object,
                public vaccineType?: Object
               
                ){

   }
      
}