import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TravelComponentComponent } from './travel-component.component';

describe('TravelComponentComponent', () => {
  let component: TravelComponentComponent;
  let fixture: ComponentFixture<TravelComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TravelComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TravelComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
