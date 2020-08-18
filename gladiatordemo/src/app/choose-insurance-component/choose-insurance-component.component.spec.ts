import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseInsuranceComponentComponent } from './choose-insurance-component.component';

describe('ChooseInsuranceComponentComponent', () => {
  let component: ChooseInsuranceComponentComponent;
  let fixture: ComponentFixture<ChooseInsuranceComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseInsuranceComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseInsuranceComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
