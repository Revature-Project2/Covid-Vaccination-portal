import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-special',
  templateUrl: './special.component.html',
  styleUrls: ['./special.component.scss']
})
export class SpecialComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit() {
  }

  nabi()
  {
    
    this._router.navigate(['mona']);
  }
}

