import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCardRecommendComponent } from './book-card-recommend.component';

describe('BookCardRecommendComponent', () => {
  let component: BookCardRecommendComponent;
  let fixture: ComponentFixture<BookCardRecommendComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookCardRecommendComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookCardRecommendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
