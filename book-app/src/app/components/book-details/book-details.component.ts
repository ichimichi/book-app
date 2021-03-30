import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/models/book';
import { ImageLinks } from 'src/app/models/image-links';
import { GoogleBooksService } from 'src/app/services/google-books.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css'],
})
export class BookDetailsComponent implements OnInit {
  book: Book | undefined;
  constructor(
    private activateRoute: ActivatedRoute,
    private googleBooksService: GoogleBooksService
  ) {}

  ngOnInit(): void {
    this.activateRoute.queryParams.subscribe((params) => {
      console.log(params.searchQuery);
      this.googleBooksService.getBook(params.id).subscribe(
        (res: any) => {
          console.log(res);
          this.book = res;
        },
        (err) => {
          console.error(err);
        }
      );
    });
  }

  getThumbnail(imageLinks: ImageLinks) {
    if (imageLinks) {
      return imageLinks.thumbnail;
    } else {
      return '../../../assets/books-icon.svg';
    }
  }
}
