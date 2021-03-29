import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: User | undefined;
  constructor(private localStorageService: LocalStorageService) {}

  ngOnInit(): void {
    this.user = {
      name: this.localStorageService.getName()!,
      email: this.localStorageService.getEmail()!,
      password: 'undefined',
      dob: '',
    };
  }
}
