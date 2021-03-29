import { Component, Input, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { ImageLinks } from 'src/app/models/image-links';
import { RecommendationService } from 'src/app/services/recommendation.service';

@Component({
  selector: 'app-book-card-recommend',
  templateUrl: './book-card-recommend.component.html',
  styleUrls: ['./book-card-recommend.component.css'],
})
export class BookCardRecommendComponent implements OnInit {
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

  removeBook(book: Book) {
    this.recommendationService.removeBook(book.id).subscribe(
      (res) => {
        console.log(res);
        alert('Successfully removed book from recommendations');
      },
      (err) => {
        console.error(err);
      }
    );
  }
}
