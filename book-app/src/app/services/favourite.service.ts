import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../models/book';
import { Observable } from 'rxjs';
import { UserAuthenticationService } from './user-authentication.service';

@Injectable({
  providedIn: 'root',
})
export class FavouriteService {
  port = '8082';
  api_endpoint = `http://localhost:${this.port}/api/v1/fav`;

  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthenticationService
  ) {}
  // getfavs() {
  //   return this.httpClient.get<Book[]>(this.api_endpoint, {
  //     headers: {
  //       Authorization: `Bearer ${this.userAuthService.getToken()}`,
  //     },
  //   });
  // }
  // errhandler(error:HttpErrorResponse){
  //   return Observable.throw(error.message || 'Server Error');

  // }
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
