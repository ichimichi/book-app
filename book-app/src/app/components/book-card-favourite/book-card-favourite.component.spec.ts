import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCardFavouriteComponent } from './book-card-favourite.component';

describe('BookCardFavouriteComponent', () => {
  let component: BookCardFavouriteComponent;
  let fixture: ComponentFixture<BookCardFavouriteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookCardFavouriteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookCardFavouriteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
