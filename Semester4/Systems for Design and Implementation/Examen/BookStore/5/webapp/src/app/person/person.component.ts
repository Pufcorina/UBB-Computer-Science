import { Component, OnInit, Input } from '@angular/core';
import { PersonService } from './shared/person.service';
import { Person } from './shared/person.module';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
  visible: boolean = false;
  personName: string;
  persons: Array<Person>;
  validSSN : boolean = true;

  constructor(public personService: PersonService) { }

  ngOnInit() {
    this.personService.getPersons(this.personName)
      .subscribe(persons => this.persons = persons);
  }

  loadClicked(): void {
    this.visible = true;
    this.personService.getPersons(this.personName)
      .subscribe(persons => this.persons = persons);
  }

  ssnChange(ssn: string): void {
    this.personService.checkUnique(ssn)
      .subscribe(validSSN => this.validSSN = validSSN);
  }


  newClicked(): void {
    this.visible = true;
  }

  saveClicked(ssn: string){
    this.visible = false;
    this.add(this.personName, ssn);
  }

  add(name: string, ssn: string): void {
    name = name.trim();
    if (!name){
      return;
    }

    ssn = ssn.trim();
    if (!ssn){
      return;
    }

    this.personService.addPerson({name, ssn} as Person)
      .subscribe(person => {this.persons.push(person);});
  }

}
