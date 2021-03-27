import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { GoogleBooksService } from 'src/app/services/google-books.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
  searchForm = new FormGroup({
    searchQuery: new FormControl('', [Validators.required]),
  });

  constructor(private googleBooksService: GoogleBooksService) {}

  ngOnInit(): void {}

  search() {
    if (this.searchForm.valid) {
      console.log(this.searchForm.value);
      this.googleBooksService
        .search(this.searchForm.value.searchQuery)
        .subscribe(
          (res: any) => {
            console.log(res.items);
          },
          (err) => {
            console.error(err);
          }
        );
    } else {
      alert('something wrong');
    }
  }
}
