import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/models/user';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';
import { UserService } from 'src/app/services/user.service';

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

  constructor(private userAuthService: UserAuthenticationService) {}

  ngOnInit(): void {}

  register() {
    if (this.registerForm.valid) {
      console.log(this.registerForm.value);
      this.userAuthService.register(this.registerForm.value);
    } else {
      alert('Something went wrong');
    }
  }
}
