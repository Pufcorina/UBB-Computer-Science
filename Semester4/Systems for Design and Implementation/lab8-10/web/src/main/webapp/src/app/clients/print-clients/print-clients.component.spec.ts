import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrintClientsComponent } from './print-clients.component';

describe('PrintClientsComponent', () => {
  let component: PrintClientsComponent;
  let fixture: ComponentFixture<PrintClientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrintClientsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrintClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
