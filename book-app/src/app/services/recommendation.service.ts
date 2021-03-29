import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../models/book';
import { UserAuthenticationService } from './user-authentication.service';

@Injectable({
  providedIn: 'root',
})
export class RecommendationService {
  port = '8083';
  api_endpoint = `http://localhost:${this.port}/api/v1/recommendation`;
  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthenticationService
  ) {}

  getAll() {
    return this.httpClient.get(this.api_endpoint, {
      headers: {
        Authorization: `Bearer ${this.userAuthService.getToken()}`,
      },
    });
  }

  removeBook(bookId: string) {
    return this.httpClient.delete(this.api_endpoint, {
      params: {
        bookId: bookId,
      },
      headers: {
        Authorization: `Bearer ${this.userAuthService.getToken()}`,
      },
    });
  }

  addBook(book: Book) {
    return this.httpClient.post(this.api_endpoint, book, {
      headers: {
        Authorization: `Bearer ${this.userAuthService.getToken()}`,
      },
    });
  }
}
