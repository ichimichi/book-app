import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class RouterService {
  constructor(private router: Router) {}

  goToDashboard() {
    this.router.navigate(['/dashboard/search']);
  }
  goToLogin() {
    this.router.navigate(['/login']);
  }
  goToFavourite() {
    // this.router.navigateByUrl('/dashboard/favourite', location.reload());
    this.router.navigate(['/dashboard/recommendation']);
    this.router.navigate(['/dashboard/favourite']);

    // location.reload();
  }
  goToRecommendation() {
    this.router.navigate(['/dashboard/recommendation']);
  }
}
