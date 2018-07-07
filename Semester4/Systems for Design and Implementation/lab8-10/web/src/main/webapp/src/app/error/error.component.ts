import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {
  private static errors: string[] = [];
  static addError(err): void {
    console.log('Adding an error!');
    console.log(ErrorComponent.errors);
    console.log(err);
    ErrorComponent.errors.push(err.error.message);
    console.log('Finished adding an error!');
  }
  get staticErrors() {
    return ErrorComponent.errors;
  }
  constructor() { }

  ngOnInit() {
  }




}
