import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Book } from 'src/app/models/book';
import { DialogData } from 'src/app/models/dialog-data';
import { ImageLinks } from 'src/app/models/image-links';
import { FavouriteService } from 'src/app/services/favourite.service';
import { RecommendationService } from 'src/app/services/recommendation.service';
import { DialogAlertComponent } from '../dialog-alert/dialog-alert.component';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css'],
})
export class BookCardComponent implements OnInit {
  @Input() book: Book | undefined;
  @Input() type: string | undefined;
  dialogData: DialogData | undefined;

  constructor(
    private recommendationService: RecommendationService,
    private favouriteService: FavouriteService,
    public dialog: MatDialog
  ) {}

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
        this.dialogData = {
          title: 'Recommendation Complete',
          message: 'Book has been recommended',
          description: '',
          buttonText: [],
          redirect: [],
        };
        this.openDialog(this.dialogData);
      },
      (err) => {
        console.error(err);
        if (err.status === 409) {
          this.dialogData = {
            title: 'Failed to recommend boook',
            message: 'You have recommended this book already ',
            description: '',
            buttonText: [],
            redirect: [],
          };
          this.openDialog(this.dialogData);
        } else if (err.status === 0) {
          this.dialogData = {
            title: 'Failed to recommend boook',
            message: 'You need to be logged in to start recommending books',
            description: '',
            buttonText: ['Sign In', 'Later'],
            redirect: ['/login'],
          };
          this.openDialog(this.dialogData);
        }
      }
    );
  }

  addToFavourite(book: Book) {
    this.favouriteService.addBook(book).subscribe(
      (res) => {
        console.log(res);
        this.dialogData = {
          title: 'Added to Favourites',
          message: 'Book has been successfully added to favourites',
          description: '',
          buttonText: [],
          redirect: [],
        };
        this.openDialog(this.dialogData);
      },
      (err) => {
        console.log(err);

        if (err.status === 409) {
          this.dialogData = {
            title: 'Failed to add book to favourites',
            message: 'Book already exists',
            description: '',
            buttonText: [],
            redirect: [],
          };
          this.openDialog(this.dialogData);
        } else if (err.status === 0) {
          this.dialogData = {
            title: 'Oops Something went wrong',
            message:
              'You need to be logged in to start adding books to your favourite',
            description: '',
            buttonText: ['Sign In', 'Later'],
            redirect: ['/login'],
          };
          this.openDialog(this.dialogData);
        }
      }
    );
  }

  removeFromFavourite(book: Book) {
    this.favouriteService.removeBook(book.id).subscribe(
      (res) => {
        this.dialogData = {
          title: 'Removed from Favourites',
          message: 'Book has been successfully removed from favourites',
          description: '',
          buttonText: [],
          redirect: [],
        };
        this.openDialog(this.dialogData);
      },
      (err) => {
        console.log(err);
        this.dialogData = {
          title: 'Oops Something went wrong',
          message: 'Could not remove book from favourites',
          description: '',
          buttonText: [],
          redirect: [],
        };
        this.openDialog(this.dialogData);
      }
    );
  }

  openDialog(dialogData: DialogData) {
    this.dialog.open(DialogAlertComponent, { data: dialogData });
  }
}
