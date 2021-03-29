import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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

  constructor(
    private googleBooksService: GoogleBooksService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  search() {
    if (this.searchForm.valid) {
      console.log(this.searchForm.value);
      this.router.navigate(['/dashboard/searchresult'], {
        queryParams: {
          searchQuery: this.searchForm.value.searchQuery,
        },
      });
    } else {
      alert('something wrong');
    }
  }
}
