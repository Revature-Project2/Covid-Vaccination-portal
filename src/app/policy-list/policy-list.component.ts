import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/service.service';
import { Policy } from 'src/app/policy.model';

@Component({
  selector: 'policy-list',
  templateUrl: './policy-list.component.html',
  styleUrls: ['./policy-list.component.scss']
})
export class PolicyListComponent implements OnInit {

  policies: Policy[];
  constructor(private _ServiceService: ServiceService) { }

  ngOnInit() {
    /* this._ServiceService.getPolicies().subscribe(data => {
      this.policies = data.map(e => {
        return {
          id: e.payload.doc.id,
          ...e.payload.doc.data()
        } as Policy;
      })
    }); */
  }

  create(policy: Policy){
      this._ServiceService.createPolicy(policy);
  }

  update(policy: Policy) {
    this._ServiceService.updatePolicy(policy);
  }

  delete(id: string) {
    alert("done");
    this._ServiceService.deletePolicy(id);
  }
}