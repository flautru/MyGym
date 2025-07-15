import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentTableau } from './equipment-tableau';

describe('EquipmentTableau', () => {
  let component: EquipmentTableau;
  let fixture: ComponentFixture<EquipmentTableau>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EquipmentTableau]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EquipmentTableau);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
