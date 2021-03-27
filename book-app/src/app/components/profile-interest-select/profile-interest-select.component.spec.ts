import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileInterestSelectComponent } from './profile-interest-select.component';

describe('ProfileInterestSelectComponent', () => {
  let component: ProfileInterestSelectComponent;
  let fixture: ComponentFixture<ProfileInterestSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileInterestSelectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileInterestSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
