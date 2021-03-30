import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { RouterService } from '../services/router.service';
import { UserAuthenticationService } from '../services/user-authentication.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private useAuthService: UserAuthenticationService,
    private routeService: RouterService
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const isAuthenticated = this.useAuthService.isLoggedIn();
    if (!isAuthenticated) {
      this.routeService.goto404();
    }
    return isAuthenticated;
  }
}
