import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrintMovieComponent } from './print-movie.component';

describe('PrintMovieComponent', () => {
  let component: PrintMovieComponent;
  let fixture: ComponentFixture<PrintMovieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrintMovieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrintMovieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
