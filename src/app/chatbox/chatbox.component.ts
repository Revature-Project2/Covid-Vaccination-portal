import { Component, OnInit, ViewChild } from '@angular/core';
import { ChatService, Message } from '../chat.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/scan';
import * as $ from 'jquery';
import { ScrollBottomDirective } from '../scroll-bottom.directive';



@Component({
  selector: 'app-chatbox',
  templateUrl: './chatbox.component.html',
  styleUrls: ['./chatbox.component.scss']
})
export class ChatboxComponent implements OnInit {
  @ViewChild(ScrollBottomDirective)
  scroll: ScrollBottomDirective;
  messages: Observable<Message[]>;
  formValue: string;

  constructor(public chat: ChatService) { }

  ngOnInit() {
    // appends to array after each new message is added to feedSource
    this.messages = this.chat.conversation.asObservable()
        .scan((acc, val) => acc.concat(val) );

    

  }

  sendMessage() {
    this.chat.converse(this.formValue);
    this.formValue = '';
  }

  
  
    
    
}
