import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { ProfileListComponent } from './components/profile-list/profile-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileFormComponent,
    ProfileListComponent,
    NavbarComponent,
    ProfileViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
