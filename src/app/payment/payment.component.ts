import { Component, OnInit} from '@angular/core';
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  public payPalConfig?: IPayPalConfig;
  
  showSuccess: boolean;

    ngOnInit(): void {
      this.initConfig();
    }


    private initConfig(): void {
    
      this.payPalConfig = {
      currency: 'INR',
      clientId: 'Abx5Wdi_zlkLA-ZJ1RBXMyCUPBeDlwo8laWrVpQHsom3QzRpye7w4iwhpzsKjdYw60R-wHqEBIbXOL-9',
      createOrderOnClient: (data) => <ICreateOrderRequest>{
        intent: 'CAPTURE',
        purchase_units: [
          {
            amount: {
              currency_code: 'INR',
              value: '9.99',
              breakdown: {
                item_total: {
                  currency_code: 'INR',
                  value: '9.99'
                }
              }
            },
            items: [
              {
                name: 'Enterprise Subscription',
                quantity: '1',
                category: 'DIGITAL_GOODS',
                unit_amount: {
                  currency_code: 'INR',
                  value: '9.99',
                },
              }
            ]
          }
        ]
      },
      advanced: {
        commit: 'true'
      },
      style: {
        label: 'paypal',
        layout: 'vertical'
      },
      onApprove: (data, actions) => {
        actions.order.get().then(details => {
        });
      },
      onClientAuthorization: (data) => {
        this.showSuccess = true;
      },
      onCancel: (data, actions) => {
        console.log('');
      },
      onError: err => {
        console.log('');
      },
      onClick: (data, actions) => {
        
        console.log('');
      },
    };
    }
}
