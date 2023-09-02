import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileListComponent } from './components/profile-list/profile-list.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';
import { CompanyListComponent } from './components/company-list/company-list.component';
import { CompanyFormComponent } from './components/company-form/company-form.component';
import { CompanyViewComponent } from './components/company-view/company-view.component';
import { JobRoleListComponent } from './components/job-role-list/job-role-list.component';
import { JobRoleFormComponent } from './components/job-role-form/job-role-form.component';
import { JobRoleViewComponent } from './components/job-role-view/job-role-view.component';

const routes: Routes = [
  { path: 'profiles', component: ProfileListComponent },
  { path: 'profile', component: ProfileFormComponent },
  { path: 'profile/:id', component: ProfileFormComponent },
  { path: 'profile/:id/view', component: ProfileViewComponent },

  { path: 'companies', component: CompanyListComponent },
  { path: 'company', component: CompanyFormComponent },
  { path: 'company/:id', component: CompanyFormComponent },
  { path: 'company/:id/view', component: CompanyViewComponent },

  { path: 'job-roles', component: JobRoleListComponent },
  { path: 'job-role', component: JobRoleFormComponent },
  { path: 'job-role/:id', component: JobRoleFormComponent },
  { path: 'job-role/:id/view', component: JobRoleViewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
