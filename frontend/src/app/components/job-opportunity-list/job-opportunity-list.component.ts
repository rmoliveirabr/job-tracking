import { Component, OnInit } from "@angular/core";
import { JobOpportunity } from "src/app/model/JobOpportunity";
import { JobOpportunityService } from "src/app/services/job-opportunity.service";

@Component({
  selector: "app-job-opportunity-list",
  templateUrl: "./job-opportunity-list.component.html",
  styleUrls: ["./job-opportunity-list.component.css"],
})
export class JobOpportunityListComponent implements OnInit {
  jobOpportunities: JobOpportunity[] = [];

  constructor(private jobOpportunityService: JobOpportunityService) {}

  async ngOnInit() {
    this.jobOpportunities =
      await this.jobOpportunityService.getJobOpportunities();
    console.log("Returned job opportunities:", this.jobOpportunities);
  }

  async deleteJobOpportunity(id: number) {
    let result: boolean = await this.jobOpportunityService.deleteJobOpportunity(
      id
    );
    if (result)
      this.jobOpportunities =
        await this.jobOpportunityService.getJobOpportunities();
  }
}
