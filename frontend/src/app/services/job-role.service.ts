import { Injectable } from '@angular/core';
import { JobRole } from '../model/JobRole';
import { RestService } from './restService';

@Injectable({
  providedIn: 'root'
})
export class JobRoleService {
  restService: RestService<JobRole> = new RestService<JobRole>("http://localhost:8080/api/job-roles");

  async getJobRoles(): Promise<JobRole[]> {
    const jobRoles:JobRole[] = await this.restService.getAll();
    console.log('Retrieved job roles:', jobRoles);

    return jobRoles;
  }

  async getJobRole(id:number): Promise<JobRole> {
    const jobRole:JobRole = await this.restService.getById(id);
    console.log('Getting job role for:', id, jobRole);

    return jobRole;
  }

  async deleteJobRole(id:number): Promise<boolean> {
    const result = await this.restService.delete(id);    
    console.log('Deleted job role for:', id);

    return result;
  }

  async upsertJobRole(jobRole:JobRole): Promise<number> {
    if (jobRole.id === 0) {
      await this.restService.create(jobRole);    
    } else {
      await this.restService.update(jobRole.id, jobRole);    
    }

    console.log('Upserted job role for:', jobRole.id);
    
    return jobRole.id;
  }
}
