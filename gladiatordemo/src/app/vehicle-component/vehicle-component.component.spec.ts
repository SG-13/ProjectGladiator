import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleComponentComponent } from './vehicle-component.component';

describe('VehicleComponentComponent', () => {
  let component: VehicleComponentComponent;
  let fixture: ComponentFixture<VehicleComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
