import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Person } from '../person/person-shared/person.module';
import { PersonService } from '../person/person.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  books: Array<Book>;

  constructor(
    private route: ActivatedRoute,
    private personService: PersonService,
    private location: Location
  ) {}

  ngOnInit() {
    const pid = +this.route.snapshot.paramMap.get('pid');
    this.personService.getBooksByPid(pid)
      .subscribe(books => this.books = books);
  }

}
