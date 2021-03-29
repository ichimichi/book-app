import { Component, Input, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { ImageLinks } from 'src/app/models/image-links';
import { FavouriteService } from 'src/app/services/favourite.service';

@Component({
  selector: 'app-book-card-favourite',
  templateUrl: './book-card-favourite.component.html',
  styleUrls: ['./book-card-favourite.component.css'],
})
export class BookCardFavouriteComponent implements OnInit {
  @Input() book: Book | undefined;
  constructor(private favouriteService: FavouriteService) {}

  ngOnInit(): void {}

  getThumbnail(imageLinks: ImageLinks) {
    if (imageLinks) {
      return imageLinks.thumbnail;
    } else {
      return '../../../assets/books-icon.svg';
    }
  }
  removeBook(book: Book) {
    this.favouriteService.removeBook(book.id).subscribe(
      (res) => {
        console.log(res);
        alert('Successfully removed book from Favourites');
      },
      (err) => {
        console.error(err);
      }
    );
  }
}
