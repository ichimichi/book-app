import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  registerForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    date: new FormControl('', [Validators.required]),
  });

  constructor(
    private userAuthService: UserAuthenticationService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  register() {
    if (this.registerForm.valid) {
      console.log(this.registerForm.value);

      let user: User = {
        name: this.registerForm.value.name,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password,
        dob: this.registerForm.value.date,
      };

      this.userAuthService.register(user);
    } else {
      alert('Something went wrong');
    }
  }
}
