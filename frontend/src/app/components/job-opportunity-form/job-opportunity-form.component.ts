import { Component, ElementRef, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable, map, startWith } from "rxjs";
import { JobOpportunity } from "src/app/model/JobOpportunity";
import { Company } from "src/app/model/Company";
import { JobOpportunityService } from "src/app/services/job-opportunity.service";
import { JobRoleService } from "src/app/services/job-role.service";
import { JobRole } from "src/app/model/JobRole";
import { CompanyService } from "src/app/services/company.service.service";

@Component({
  selector: "app-job-opportunity-form",
  templateUrl: "./job-opportunity-form.component.html",
  styleUrls: ["./job-opportunity-form.component.css"],
})
export class JobOpportunityFormComponent implements OnInit {
  public jobOpportunityForm: FormGroup;
  public action = "Add";

  public id: number;
  public jobOpportunity?: JobOpportunity;

  public roles: JobRole[] = [];
  public filteredRoles?: Observable<string[]>;

  public companies: Company[] = [];
  public filteredHiringCompanies?: Observable<string[]>;
  public filteredRecruitmentCompanies?: Observable<string[]>;

  constructor(
    private jobOpportunityService: JobOpportunityService,
    private jobRoleService: JobRoleService,
    private companyService: CompanyService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private elementRef: ElementRef
  ) {
    this.jobOpportunityForm = this.fb.group({
      role: ["", Validators.required],
      hiringCompany: [""],
      recruitmentCompany: [""],
      jobUrl: [""],
      jobDescription: [""],
    });

    this.id = parseInt(this.route.snapshot.paramMap.get("id")!);
  }

  async ngOnInit() {
    if (this.id) {
      // edit case
      this.action = "Edit";
      this.jobOpportunity = await this.jobOpportunityService.getJobOpportunity(this.id);
      this.jobOpportunityForm.patchValue({
        role: this.jobOpportunity.role.name,
        hiringCompany: this.jobOpportunity.hiringCompany?.name || undefined,
        recruitmentCompany: this.jobOpportunity.recruitmentCompany?.name || undefined,
        jobUrl: this.jobOpportunity.jobUrl,
        jobDescription: this.jobOpportunity.jobDescription,
      });
    }
    this.roles = await this.jobRoleService.getJobRoles();
    this.filteredRoles = this.jobOpportunityForm.get("role")?.valueChanges.pipe(
      startWith(""),
      map((value) => this._filterJobRole(value || ""))
    );

    this.companies = await this.companyService.getCompanies();
    this.filteredHiringCompanies = this.jobOpportunityForm.get("hiringCompany")?.valueChanges.pipe(
      startWith(""),
      map((value) => this._filterCompany(value || ""))
    );

    this.filteredRecruitmentCompanies = this.jobOpportunityForm.get("recruitmentCompany")?.valueChanges.pipe(
      startWith(""),
      map((value) => this._filterCompany(value || ""))
    );
  }

  private _filterJobRole(value: string): string[] {
    const filtered: JobRole[] = this.roles.filter((option) => option.name.toLowerCase().includes(value.toLowerCase()));

    return filtered.map((value) => value.name);
  }

  private _filterCompany(value: string): string[] {
    const filtered: Company[] = this.companies.filter((option) => option.name.toLowerCase().includes(value.toLowerCase()));

    return filtered.map((value) => value.name);
  }

  private async getRole(roles: JobRole[], roleElem: any): Promise<JobRole> {
    var role = roles.find((value) => value.name === roleElem.value);
    if (!role) {
      console.log(`Didn't find role ${roleElem.value}, creating`);
      role = {
        id: 0,
        name: roleElem.value,
      };
      role.id = await this.jobRoleService.upsertJobRole(role);
    }
    return role;
  }

  private async getCompany(companies: Company[], companyElem: any): Promise<Company | undefined> {
    var company = companies.find((value) => value.name === companyElem.value);
    if (!company && companyElem.value) {
      console.log(`Didn't find company ${companyElem.value}, creating`);
      company = {
        id: 0,
        name: companyElem.value,
      };
      company.id = await this.companyService.upsertCompany(company);
    }

    return company;
  }

  async addEditJobOpportunity() {
    // get elements by id
    var role = await this.getRole(this.roles, this.jobOpportunityForm.get("role"));
    var hiringCompany = await this.getCompany(this.companies, this.jobOpportunityForm.get("hiringCompany"));
    var recruitmentCompany = await this.getCompany(this.companies, this.jobOpportunityForm.get("recruitmentCompany"));

    // check for new object
    if (!this.jobOpportunity) {
      this.jobOpportunity = {
        id: 0,
        hiringCompany: hiringCompany || undefined,
        recruitmentCompany: recruitmentCompany || undefined,
        role: role,
        jobUrl: this.jobOpportunityForm.get("jobUrl")?.value,
        jobDescription: "",
      };
    } else {
      // update
      this.jobOpportunity.hiringCompany = hiringCompany;
      this.jobOpportunity.recruitmentCompany = recruitmentCompany;
      this.jobOpportunity.role = role;
      this.jobOpportunity.jobUrl = this.jobOpportunityForm.get("jobUrl")?.value;
      this.jobOpportunity.jobDescription = this.jobOpportunityForm.get("jobDescription")?.value;
    }

    let id = await this.jobOpportunityService.upsertJobOpportunity(this.jobOpportunity);

    this.router.navigate(["/job-opportunities"]);
    console.log("upserted:", this.jobOpportunity);
  }
}
