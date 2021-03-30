import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/models/book';
import { FavouriteService } from 'src/app/services/favourite.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css'],
})
export class FavouriteComponent implements OnInit {
  bookList: Array<Book> = [];

  constructor(
    private favService: FavouriteService,
    private activateRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.favService.getAll().subscribe(
      (res: any) => {
        console.log(res);
        this.bookList = res;
      },
      (err) => {
        console.error(err);
      }
    );
  }
}
