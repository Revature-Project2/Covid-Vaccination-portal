import { Time } from "@angular/common";

export class Clinic{
    public get clinicName(): string {
        return this._clinicName;
    }
    public set clinicName(value: string) {
        this._clinicName = value;
    }
    public get closingTime(): Time {
        return this._closingTime;
    }
    public set closingTime(value: Time) {
        this._closingTime = value;
    }
    public get openingTime(): Time {
        return this._openingTime;
    }
    public set openingTime(value: Time) {
        this._openingTime = value;
    }
    public get numberOfBeds(): number {
        return this._numberOfBeds;
    }
    public set numberOfBeds(value: number) {
        this._numberOfBeds = value;
    }
    /** 
     * public Clinic(int clinicId, String clinicName, String clinicAddress, int numberOfBeds, Time openingTime,
			Time closingTime, List<Appointment> appointmentList, List<TimeslotJoinClinic> timeslotClinicList)
    public Clinic(String clinicName, String clinicAddress, int numberOfBeds, Time openingTime,
			Time closingTime) {
            */
    constructor(private _clinicName: string,private clinicAddress:string,
        private _numberOfBeds: number,private _openingTime: Time,private _closingTime: Time)
    {

    }
}