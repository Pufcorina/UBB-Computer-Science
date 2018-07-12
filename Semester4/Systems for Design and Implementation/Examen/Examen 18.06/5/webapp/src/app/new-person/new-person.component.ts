import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-person',
  templateUrl: './new-person.component.html',
  styleUrls: ['./new-person.component.css']
})
export class NewPersonComponent implements OnInit {
  visible: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  saveClicked(){
    this.visible = true;
  }
}
