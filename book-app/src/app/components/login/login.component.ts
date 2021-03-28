import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
    private router: Router
  ) {}

  ngOnInit(): void {}

  login() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);
    } else {
      alert('Something went wrong');
    }
  }
}
