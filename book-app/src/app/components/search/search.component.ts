import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  searchForm = new FormGroup({
    searchQuery: new FormControl('', [Validators.required]),
  });

  constructor() {}

  ngOnInit(): void {}

  search() {
    if (this.searchForm.valid) {
      console.log(this.searchForm.value);
    } else {
      alert('something wrong');
    }
  }
}
