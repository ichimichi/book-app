import { utf8Encode } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/models/book';
import { ImageLinks } from 'src/app/models/image-links';
import { GoogleBooksService } from 'src/app/services/google-books.service';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css'],
})
export class SearchResultComponent implements OnInit {
  bookList: Array<Book> = [];

  constructor(
    private googleBooksService: GoogleBooksService,
    private activateRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activateRoute.queryParams.subscribe((params) => {
      console.log(params.searchQuery);
      this.googleBooksService.search(params.searchQuery).subscribe(
        (res: any) => {
          console.log(res.items);
          this.bookList = res.items;
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
