import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GoogleBooksService {
  key = 'AIzaSyC7ghadkynQkFcZIyXvMmnmmee6hMvNYts';
  api_endpoint = 'https://www.googleapis.com/books/v1/volumes';
  constructor(private httpClient: HttpClient) {}

  search(query: string) {
    return this.httpClient.get(this.api_endpoint, {
      params: {
        key: this.key,
        q: query,
        maxResults: '40',
      },
    });
  }

  getBook(id: string) {
    return this.httpClient.get(`${this.api_endpoint}/${id}`, {
      params: {
        key: this.key,
      },
    });
  }
}
