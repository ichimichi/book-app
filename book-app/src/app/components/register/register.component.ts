import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DialogData } from 'src/app/models/dialog-data';
import { RouterService } from 'src/app/services/router.service';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';
import { DialogAlertComponent } from '../dialog-alert/dialog-alert.component';

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
  dialogData: DialogData | undefined;

  constructor(
    private userAuthService: UserAuthenticationService,
    public dialog: MatDialog,
    private routerService: RouterService
  ) {}

  ngOnInit(): void {}

  register() {
    if (this.registerForm.valid) {
      console.log(this.registerForm.value);
      this.userAuthService.register(this.registerForm.value).subscribe(
        (res: any) => {
          console.log(res);
          this.dialogData = {
            title: 'Registration Successfull',
            message: 'Please Sign In to continue',
            description: '',
            buttonText: ['Okay'],
            redirect: ['/login'],
          };
          this.openDialog(this.dialogData);
        },
        (err) => {
          this.dialogData = {
            title: 'Oops Something went wrong',
            message:
              'User with given email already exists or you have entered an invalid input',
            description: '',
            buttonText: ['Okay'],
            redirect: [''],
          };
          this.openDialog(this.dialogData);
          console.error(err);
        }
      );
    } else {
      this.dialogData = {
        title: 'Oops Something went wrong',
        message:
          'User with given email already exists or you have entered an invalid input',
        description: '',
        buttonText: ['Okay'],
        redirect: [''],
      };
      this.openDialog(this.dialogData);
    }
  }

  openDialog(dialogData: DialogData) {
    this.dialog.open(DialogAlertComponent, { data: dialogData });
  }

  gotoLogin() {
    this.routerService.goToLogin();
  }
}
