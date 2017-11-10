import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomiesComponent } from './roomies.component';

describe('RoomiesComponent', () => {
  let component: RoomiesComponent;
  let fixture: ComponentFixture<RoomiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
