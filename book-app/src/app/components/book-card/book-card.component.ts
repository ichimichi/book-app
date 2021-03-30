import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { DialogData } from 'src/app/models/dialog-data';
import { ImageLinks } from 'src/app/models/image-links';
import { RecommendationService } from 'src/app/services/recommendation.service';
import { DialogAlertComponent } from '../dialog-alert/dialog-alert.component';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.css'],
})
export class BookCardComponent implements OnInit {
  @Input() book: Book | undefined;
  dialogData: DialogData | undefined;

  constructor(
    private recommendationService: RecommendationService,
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
        alert('Succesfully added book to recommendations');
      },
      (err) => {
        console.error(err);
        this.dialogData = {
          title: 'Failed to recommend boook',
          message: 'You need to be logged in to start recommending books',
          description: '',
          buttonText: ['Sign In', 'Later'],
          redirect: ['/login'],
        };
        this.openDialog(this.dialogData);
      }
    );
  }

  addToFavourite(book: Book) {
    console.log('hi');
  }

  openDialog(dialogData: DialogData) {
    this.dialog.open(DialogAlertComponent, { data: dialogData });
  }
}
