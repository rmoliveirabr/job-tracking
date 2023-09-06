import { Injectable } from "@angular/core";
import { RestService } from "./restService";
import { JobOpportunity } from "../model/JobOpportunity";

@Injectable({
  providedIn: "root",
})
export class JobOpportunityService {
  restService: RestService<JobOpportunity> = new RestService<JobOpportunity>(
    "http://localhost:8080/api/job-opportunities"
  );

  async getJobOpportunities(): Promise<JobOpportunity[]> {
    const jobOpportunities: JobOpportunity[] = await this.restService.getAll();
    console.log("Retrieved job opportunities:", jobOpportunities);

    return jobOpportunities;
  }

  async getJobOpportunity(id: number): Promise<JobOpportunity> {
    const jobOpportunity: JobOpportunity = await this.restService.getById(id);
    console.log("Getting job opportunity for:", id, jobOpportunity);

    return jobOpportunity;
  }

  async deleteJobOpportunity(id: number): Promise<boolean> {
    const result = await this.restService.delete(id);
    console.log("Deleted job opportunity for:", id);

    return result;
  }

  async upsertJobOpportunity(jobOpportunity: JobOpportunity): Promise<number> {
    if (jobOpportunity.id === 0) {
      jobOpportunity = await this.restService.create(jobOpportunity);
    } else {
      await this.restService.update(jobOpportunity.id, jobOpportunity);
    }

    console.log("Upserted job opportunity for:", jobOpportunity.id);

    return jobOpportunity.id;
  }
}
