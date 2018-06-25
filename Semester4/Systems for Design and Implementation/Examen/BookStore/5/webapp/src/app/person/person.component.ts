import { Component, OnInit, Input } from '@angular/core';
import { PersonService } from './shared/person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  visible: boolean = false;
  personName: string;
  persons: Array<Person>;

  constructor(public personService: PersonService) { }

  ngOnInit() {
  }

  loadClicked(): void {
    this.visible = true;
    this.personService.getPersonsByName(this.personName)
      .subscribe(persons => this.persons = persons);
  }

}
