import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class RecommendationService {
  port = '8083';
  api_endpoint = `http://localhost:${this.port}/api/v1/auth`;
  constructor(private httpClient: HttpClient) {}
}
