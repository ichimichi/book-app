import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserAuthenticationService {
  port = '8084';
  api_endpoint = `http://localhost:${this.port}/api/v1/auth`;
  constructor(private httpClient: HttpClient) {}
}
