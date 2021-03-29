import { Component, Input, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { ImageLinks } from 'src/app/models/image-links';
import { RecommendationService } from 'src/app/services/recommendation.service';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css'],
})
export class BookCardComponent implements OnInit {
  @Input() book: Book | undefined;
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit(): void {}

  getThumbnail(imageLinks: ImageLinks) {
    if (imageLinks) {
      return imageLinks.thumbnail;
    } else {
      return '../../../assets/books-icon.svg';
    }
  }

  recommendBook(book: Book) {
    this.recommendationService.recommendBook(book).subscribe(
      (res) => {
        console.log(res);
        alert('Succesfully added book to recommendations');
      },
      (err) => {
        console.error(err);
      }
    );
  }

  addToFavourite(book: Book) {}
}
