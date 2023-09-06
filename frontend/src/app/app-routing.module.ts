import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ProfileListComponent } from "./components/profile-list/profile-list.component";
import { ProfileFormComponent } from "./components/profile-form/profile-form.component";
import { ProfileViewComponent } from "./components/profile-view/profile-view.component";
import { CompanyListComponent } from "./components/company-list/company-list.component";
import { CompanyFormComponent } from "./components/company-form/company-form.component";
import { CompanyViewComponent } from "./components/company-view/company-view.component";
import { JobRoleListComponent } from "./components/job-role-list/job-role-list.component";
import { JobRoleFormComponent } from "./components/job-role-form/job-role-form.component";
import { JobRoleViewComponent } from "./components/job-role-view/job-role-view.component";
import { JobOpportunityListComponent } from "./components/job-opportunity-list/job-opportunity-list.component";
import { JobOpportunityFormComponent } from "./components/job-opportunity-form/job-opportunity-form.component";
import { JobOpportunityViewComponent } from "./components/job-opportunity-view/job-opportunity-view.component";
import { JobApplicationListComponent } from "./components/job-application-list/job-application-list.component";
import { JobApplicationFormComponent } from "./components/job-application-form/job-application-form.component";
import { JobApplicationViewComponent } from "./components/job-application-view/job-application-view.component";

const routes: Routes = [
  { path: "profiles", component: ProfileListComponent },
  { path: "profile", component: ProfileFormComponent },
  { path: "profile/:id", component: ProfileFormComponent },
  { path: "profile/:id/view", component: ProfileViewComponent },

  { path: "companies", component: CompanyListComponent },
  { path: "company", component: CompanyFormComponent },
  { path: "company/:id", component: CompanyFormComponent },
  { path: "company/:id/view", component: CompanyViewComponent },

  { path: "job-roles", component: JobRoleListComponent },
  { path: "job-role", component: JobRoleFormComponent },
  { path: "job-role/:id", component: JobRoleFormComponent },
  { path: "job-role/:id/view", component: JobRoleViewComponent },

  { path: "job-opportunities", component: JobOpportunityListComponent },
  { path: "job-opportunity", component: JobOpportunityFormComponent },
  { path: "job-opportunity/:id", component: JobOpportunityFormComponent },
  { path: "job-opportunity/:id/view", component: JobOpportunityViewComponent },

  { path: "job-applications", component: JobApplicationListComponent },
  { path: "job-application", component: JobApplicationFormComponent },
  { path: "job-application/:id", component: JobApplicationFormComponent },
  { path: "job-application/:id/view", component: JobApplicationViewComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
