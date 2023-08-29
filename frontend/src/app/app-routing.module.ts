import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileListComponent } from './components/profile-list/profile-list.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';

const routes: Routes = [
  { path: 'profiles', component: ProfileListComponent },
  { path: 'profile', component: ProfileFormComponent },
  { path: 'profile/:id', component: ProfileFormComponent },
  { path: 'profile/:id/view', component: ProfileViewComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
