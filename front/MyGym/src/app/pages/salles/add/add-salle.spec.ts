import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSalle } from '../add-salle/add-salle';

describe('AddSalle', () => {
  let component: AddSalle;
  let fixture: ComponentFixture<AddSalle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddSalle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddSalle);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
