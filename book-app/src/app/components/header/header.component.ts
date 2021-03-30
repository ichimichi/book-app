import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  searchForm = new FormGroup({
    searchQuery: new FormControl(''),
  });

  constructor(
    private userAuthService: UserAuthenticationService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  handleLogout() {
    this.userAuthService.logout();
  }

  search() {
    if (this.searchForm.value.searchQuery.length > 0) {
      console.log(this.searchForm.value);
      this.router.navigate(['/dashboard/searchresult'], {
        queryParams: {
          searchQuery: this.searchForm.value.searchQuery,
        },
      });
    } else {
      alert('something wrong');
    }
  }
}
