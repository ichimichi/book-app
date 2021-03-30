import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { DialogData } from 'src/app/models/dialog-data';
import { ImageLinks } from 'src/app/models/image-links';
import { Recommendation } from 'src/app/models/recommendation';
import { FavouriteService } from 'src/app/services/favourite.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { RecommendationService } from 'src/app/services/recommendation.service';
import { DialogAlertComponent } from '../dialog-alert/dialog-alert.component';

@Component({
  selector: 'app-book-card-recommend',
  templateUrl: './book-card-recommend.component.html',
  styleUrls: ['./book-card-recommend.component.css'],
})
export class BookCardRecommendComponent implements OnInit {
  @Input() recommendation: Recommendation | undefined;
  constructor(
    private recommendationService: RecommendationService,
    private localStorageSevice: LocalStorageService,
    private favouriteService: FavouriteService,
    public dialog: MatDialog,
    private router: Router
  ) {}

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
        this.dialogData = {
          title: 'All Good',
          message: 'Successfully removed book from recommendations',
          description: '',
          buttonText: [],
          redirect: [],
        };
        this.openDialog(this.dialogData);
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

  getNumberOfRecommendations(recommendation: Recommendation) {
    return recommendation.users.length;
  }

  isAlreadyRecommended(recommendation: Recommendation) {
    return recommendation.users.includes(this.localStorageSevice.getEmail()!);
  }

  dialogData: DialogData | undefined;

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
        console.log(err);
        this.dialogData = {
          title: 'Oops Something went wrong',
          message: 'Book already exists',
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

  showDetails(book: Book) {
    this.router.navigate(['/dashboard/details'], {
      queryParams: {
        id: book.id,
      },
    });
  }
}
