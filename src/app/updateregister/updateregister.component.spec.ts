import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateregisterComponent } from './updateregister.component';

describe('UpdateregisterComponent', () => {
  let component: UpdateregisterComponent;
  let fixture: ComponentFixture<UpdateregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateregisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
