import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterService } from 'src/app/services/router.service';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private userAuthService: UserAuthenticationService,
    private routerService: RouterService
  ) {}

  ngOnInit(): void {}

  login() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);
      this.userAuthService.login(this.loginForm.value);
    } else {
      alert('Something went wrong');
    }
  }

  goToRegister() {
    this.routerService.goToRegister();
  }
}
