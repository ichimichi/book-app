import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { SearchComponent } from './components/search/search.component';
import { FavouriteComponent } from './components/favourite/favourite.component';
import { RecommendationComponent } from './components/recommendation/recommendation.component';
import { ProfileImageUploadComponent } from './components/profile-image-upload/profile-image-upload.component';
import { ProfileInterestSelectComponent } from './components/profile-interest-select/profile-interest-select.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserAuthenticationService } from './services/user-authentication.service';
import { UserService } from './services/user.service';
import { FavouriteService } from './services/favourite.service';
import { RecommendationService } from './services/recommendation.service';
import { GoogleBooksService } from './services/google-books.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SearchResultComponent } from './components/search-result/search-result.component';
import { BookCardComponent } from './components/book-card/book-card.component';
import { BookDetailsComponent } from './components/book-details/book-details.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatTooltipModule } from '@angular/material/tooltip';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BookCardRecommendComponent } from './components/book-card-recommend/book-card-recommend.component';
import { MatBadgeModule } from '@angular/material/badge';
import {
  MatDialogModule,
  MAT_DIALOG_DEFAULT_OPTIONS,
} from '@angular/material/dialog';
import { DialogAlertComponent } from './components/dialog-alert/dialog-alert.component';
import { RouterService } from './services/router.service';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DashboardComponent,
    ProfileComponent,
    LoginComponent,
    RegisterComponent,
    SearchComponent,
    FavouriteComponent,
    RecommendationComponent,
    ProfileImageUploadComponent,
    ProfileInterestSelectComponent,
    SearchResultComponent,
    BookCardComponent,
    BookDetailsComponent,
    PageNotFoundComponent,
    BookCardRecommendComponent,
    DialogAlertComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatGridListModule,
    MatCardModule,
    MatTooltipModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatBadgeModule,
    MatDialogModule,
  ],
  providers: [
    UserAuthenticationService,
    UserService,
    FavouriteService,
    RecommendationService,
    GoogleBooksService,
    RouterService,
    { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: true } },
  ],
  entryComponents: [MatDialogModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
