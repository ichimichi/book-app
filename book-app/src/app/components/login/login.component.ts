import { LocalizedString } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogData } from 'src/app/models/dialog-data';
import { LocalStorageService } from 'src/app/services/local-storage.service';
import { RouterService } from 'src/app/services/router.service';
import { UserAuthenticationService } from 'src/app/services/user-authentication.service';
import { DialogAlertComponent } from '../dialog-alert/dialog-alert.component';

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
  dialogData: DialogData | undefined;

  constructor(
    private userAuthService: UserAuthenticationService,
    private routerService: RouterService,
    private localStrorageService: LocalStorageService,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {}

  login() {
    if (this.loginForm.valid) {
      console.log(this.loginForm.value);
      this.userAuthService.login(this.loginForm.value).subscribe(
        (res: any) => {
          console.log(res);
          this.localStrorageService.setToken(res.token);
          this.localStrorageService.setName(res.user.name);
          this.localStrorageService.setEmail(res.user.email);
          this._snackBar.open(`Welcome, ${res.user.name}`, '', {
            duration: 2000,
          });
          this.routerService.goToDashboard();
        },
        (err) => {
          this.dialogData = {
            title: 'Oops Something went wrong',
            message: 'Invalid email and password combination',
            description: '',
            buttonText: ['Okay'],
            redirect: [''],
          };
          this.openDialog(this.dialogData);
        }
      );
    } else {
      this.dialogData = {
        title: 'Oops Something went wrong',
        message: 'You have entered an invalid input',
        description: '',
        buttonText: ['Okay'],
        redirect: [''],
      };
      this.openDialog(this.dialogData);
    }
  }

  goToRegister() {
    this.routerService.goToRegister();
  }

  openDialog(dialogData: DialogData) {
    this.dialog.open(DialogAlertComponent, { data: dialogData });
  }
}
