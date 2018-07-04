import { Component, OnInit, Input } from '@angular/core';
import { Person } from '../person-shared/person.module';


@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {
  @Input() persons: Array<Person>;

  constructor() { }

  ngOnInit() {
  }

}
