import { Component, OnInit, Input } from '@angular/core';
import { PersonService } from './/person.service';
import { Person } from './person-shared/person.module';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  @Input() person: Person;
  persons: Array<Person>;

  constructor(public personService: PersonService) { }

  ngOnInit() {
    this.personService.getPersons()
      .subscribe(persons => this.persons = persons);
  }

}
