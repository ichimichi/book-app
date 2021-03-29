import { Component, Input, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { ImageLinks } from 'src/app/models/image-links';
import { Recommendation } from 'src/app/models/recommendation';
import { RecommendationService } from 'src/app/services/recommendation.service';

@Component({
  selector: 'app-book-card-recommend',
  templateUrl: './book-card-recommend.component.html',
  styleUrls: ['./book-card-recommend.component.css'],
})
export class BookCardRecommendComponent implements OnInit {
  @Input() recommendation: Recommendation | undefined;
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit(): void {}

  getThumbnail(imageLinks: ImageLinks) {
    if (imageLinks) {
      return imageLinks.thumbnail;
    } else {
      return '../../../assets/books-icon.svg';
    }
  }

  unRecommendBook(book: Book) {
    this.recommendationService.removeBookFromRecommendation(book.id).subscribe(
      (res) => {
        console.log(res);
        alert('Successfully removed book from recommendations');
      },
      (err) => {
        console.error(err);
      }
    );
  }

  recommendBook(book: Book) {
    this.recommendationService.recommendBook(book).subscribe(
      (res) => {
        console.log(res);
        alert('Successfully recommended book');
      },
      (err) => {
        console.error(err);
      }
    );
  }

  addToFavourite(book: Book) {}

  getNumberOfRecommendations(recommendation: Recommendation) {
    return recommendation.users.length;
  }
}
