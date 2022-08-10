import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewregisterComponent } from './viewregister.component';

describe('ViewregisterComponent', () => {
  let component: ViewregisterComponent;
  let fixture: ComponentFixture<ViewregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewregisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
