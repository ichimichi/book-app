import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { RecommendationService } from 'src/app/services/recommendation.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css'],
})
export class RecommendationComponent implements OnInit {
  bookList: Array<Book> = [];
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit(): void {
    this.recommendationService.getAll().subscribe(
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
