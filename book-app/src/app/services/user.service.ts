import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { UserAuthenticationService } from './user-authentication.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  port = '8081';
  api_endpoint = `http://localhost:${this.port}/api/v1/user`;
  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthenticationService
  ) {}

  addUser(user: User) {
    return this.httpClient.post(this.api_endpoint, user, {
      headers: {
        Authorization: `Bearer ${this.userAuthService.getToken()}`,
      },
    });
  }

  getUserDetails() {
    return this.httpClient.get(this.api_endpoint, {
      headers: {
        Authorization: `Bearer ${this.userAuthService.getToken()}`,
      },
    });
  }

  getUserInterests() {
    return this.httpClient.get(`${this.api_endpoint}/interest`, {
      headers: {
        Authorization: `Bearer ${this.userAuthService.getToken()}`,
      },
    });
  }
}
