import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  slides = [
    {'image': 'assets/images/vaccine9.jpg'}, 
    {'image': 'assets/images/vaccine10.jpg'}, 
    {'image': 'assets/images/vaccine11.jpg'}, 
  ];
}
