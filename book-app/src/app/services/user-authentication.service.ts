import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
    private routerService: RouterService
  ) {}

  login(user: { email: string; password: string }) {
    return this.httpClient.post(`${this.api_endpoint}/login`, user).subscribe(
      (res: any) => {
        console.log(res);
        this.localStrorageService.setToken(res.token);
        alert('Welcome!');
        this.routerService.goToDashboard();
      },
      (err) => {
        alert('Invalid credentials');
        console.error(err);
      }
    );
  }

  isLoggedIn() {
    return this.localStrorageService.getToken() ? true : false;
  }

  logout() {
    this.localStrorageService.removeToken();
    alert('Successfully logged out');
    this.routerService.goToDashboard();
  }

  getToken() {
    return this.localStrorageService.getToken();
  }
}
