import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.scss']
})
export class PriceListComponent implements OnInit {
    selectedprice:number=0;
  packagename:string="";
  constructor(private _router: Router) { }
 price:any = [
  '12',
  '13',
  '14'
 ];
  ngOnInit() {
  }

radiochangehandler (event:any)
{

  this.selectedprice = event.target.value;
  
}

ordernow(name:any)
{
  this.packagename = name;
  this._router.navigate(['payment']);

}
}
