import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  port = '8081';
  api_endpoint = `http://localhost:${this.port}/api/v1/auth`;
  constructor(private httpClient: HttpClient) {}
}
