import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(private userAuthService: UserAuthenticationService) {}

  ngOnInit(): void {}

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  handleLogout() {
    this.userAuthService.logout();
  }
}
