import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JobRole } from 'src/app/model/JobRole';
import { JobRoleService } from 'src/app/services/job-role.service';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs';

@Component({
  selector: 'app-job-role-form',
  templateUrl: './job-role-form.component.html',
  styleUrls: ['./job-role-form.component.css']
})
export class JobRoleFormComponent implements OnInit {
  public jobRoleForm: FormGroup;
  public action = 'Add';
  
  public id: number;
  public jobRole?: JobRole;

  public gradeControl = new FormControl('');
  public grades: string[] = ['Great', 'Good', 'Bad'];
  public filteredGrades?: Observable<string[]>;

  constructor(private jobRoleService:JobRoleService, private route: ActivatedRoute, 
    private router: Router, private fb: FormBuilder,
    private elementRef: ElementRef
    ) {
      this.jobRoleForm  = this.fb.group({
        name: ['', Validators.required],
        alternateNames: ['']
      });

      this.id = parseInt(this.route.snapshot.paramMap.get('id')!);
  }

  async ngOnInit() {
    if (this.id) { // edit case
      this.action = 'Edit';
      this.jobRole = await this.jobRoleService.getJobRole(this.id);
      this.jobRoleForm.patchValue({
        name: this.jobRole.name,
        alternateNames: this.jobRole.alternateNames
      });
    }
    this.filteredGrades = this.gradeControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.grades.filter(option => option.toLowerCase().includes(filterValue));
  }  

  async addEditJobRole() {
    // check for new company
    if (!this.jobRole) {
      this.jobRole = {
        id: 0,
        name: this.jobRoleForm.get('name')?.value,
        alternateNames: this.jobRoleForm.get('alternateNames')?.value,
      }
    } else { // update
      this.jobRole.name = this.jobRoleForm.get('name')?.value;
      this.jobRole.alternateNames = this.jobRoleForm.get('alternateNames')?.value;
    }

    let id = await this.jobRoleService.upsertJobRole(this.jobRole);

    this.router.navigate(['/job-roles']);
    console.log('upserted:' , this.jobRole)
  }
}
