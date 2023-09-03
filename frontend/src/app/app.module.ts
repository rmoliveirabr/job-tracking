import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { ProfileListComponent } from './components/profile-list/profile-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';
import { CompanyFormComponent } from './components/company-form/company-form.component';
import { CompanyListComponent } from './components/company-list/company-list.component';
import { CompanyViewComponent } from './components/company-view/company-view.component';
import { JobRoleListComponent } from './components/job-role-list/job-role-list.component';
import { JobRoleFormComponent } from './components/job-role-form/job-role-form.component';
import { JobRoleViewComponent } from './components/job-role-view/job-role-view.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    ProfileFormComponent,
    ProfileListComponent,
    NavbarComponent,
    ProfileViewComponent,
    CompanyFormComponent,
    CompanyListComponent,
    CompanyViewComponent,
    JobRoleListComponent,
    JobRoleFormComponent,
    JobRoleViewComponent,
    ProfileViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
