import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm=new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    username:new FormControl('', [Validators.required]),
    date:new FormControl('', [Validators.required]),


  });

  constructor(
    private userAuthService: UserAuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register(){

    if (this.registerForm.valid) {
      console.log(this.registerForm.value);
    } else {
      alert('Something went wrong');
    }
  }
}
