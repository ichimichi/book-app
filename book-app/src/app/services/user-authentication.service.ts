import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../models/user';
import { LocalStorageService } from './local-storage.service';
import { RouterService } from './router.service';

@Injectable({
  providedIn: 'root',
})
export class UserAuthenticationService {
  port = '8084';
  api_endpoint = `http://localhost:${this.port}/api/v1/auth`;

  constructor(
    private httpClient: HttpClient,
    private localStrorageService: LocalStorageService,
    private routerService: RouterService,
    private _snackBar: MatSnackBar
  ) {}

  login(user: { email: string; password: string }) {
    return this.httpClient.post(`${this.api_endpoint}/login`, user);
  }

  register(user: User) {
    return this.httpClient.post(`${this.api_endpoint}/register`, user);
  }

  isLoggedIn() {
    return this.localStrorageService.getToken() ? true : false;
  }

  logout() {
    this._snackBar.open(`Goodbye, ${this.localStrorageService.getName()}`, '', {
      duration: 2000,
    });
    this.localStrorageService.removeToken();

    this.routerService.goToDashboard();
  }

  getToken() {
    return this.localStrorageService.getToken();
  }
}
