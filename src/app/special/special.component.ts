import { Component, OnInit } from '@angular/core';
<<<<<<< HEAD
=======
import { Router } from '@angular/router';
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf

@Component({
  selector: 'app-special',
  templateUrl: './special.component.html',
  styleUrls: ['./special.component.scss']
})
export class SpecialComponent implements OnInit {

<<<<<<< HEAD
  constructor() { }
=======
  constructor(private _router: Router) { }
>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf

  ngOnInit() {
  }

<<<<<<< HEAD
}
=======
  nabi()
  {
    
    this._router.navigate(['mona']);
  }
}

>>>>>>> 4365ba1bfd158757a1e41318f3195afe7ffa50cf
