import { Component, OnInit } from '@angular/core';
import { JobRole } from 'src/app/model/JobRole';
import { JobRoleService } from 'src/app/services/job-role.service';

@Component({
  selector: 'app-job-role-list',
  templateUrl: './job-role-list.component.html',
  styleUrls: ['./job-role-list.component.css']
})
export class JobRoleListComponent implements OnInit {
  jobRoles: JobRole[] = [];

  constructor(private jobRoleService: JobRoleService) { }

  async ngOnInit() {
    this.jobRoles = await this.jobRoleService.getJobRoles();
    console.log('Returned companies:',this.jobRoles);
  }

  async deleteJobRole(id:number) {
    let result:boolean = await this.jobRoleService.deleteJobRole(id);
    if (result)
      this.jobRoles = await this.jobRoleService.getJobRoles();
  }

}
