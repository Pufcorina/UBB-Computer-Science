import { Component, OnInit, Input } from '@angular/core';
import { PersonService } from './/person.service';
import { Person } from './person-shared/person.module';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  person: Person;
  visible: boolean = false;

  constructor(public personService: PersonService) { }

  ngOnInit() {
  }

  newButtonClicked(): void {
    this.visible = true;
  }

  loadButtonClick(ssn, name): void {
    this.personService.savePerson({ssn, name} as Person).subscribe();
    this.visible = false;
  }

}
