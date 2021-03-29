import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { Recommendation } from 'src/app/models/recommendation';
import { RecommendationService } from 'src/app/services/recommendation.service';

@Component({
  selector: 'app-recommendation',
  templateUrl: './recommendation.component.html',
  styleUrls: ['./recommendation.component.css'],
})
export class RecommendationComponent implements OnInit {
  recommendationList: Array<Recommendation> = [];
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit(): void {
    this.recommendationService.getAllRecommendations().subscribe(
      (res: any) => {
        console.log(res);
        this.recommendationList = res;
      },
      (err) => {
        console.error(err);
      }
    );
  }
}
