import { Component, OnInit } from '@angular/core';
import { ResumeService } from '../../services/resume.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Resume } from '../../model/Resume';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-resume-form',
  templateUrl: './resume-form.component.html',
  styleUrls: ['./resume-form.component.css']
})
export class ResumeFormComponent {
  public resumeForm: FormGroup;
  public action = 'Add';
  
  public resume?: Resume;
  public id: number;

  constructor(private resumeService:ResumeService, private route: ActivatedRoute, 
              private router: Router, private fb: FormBuilder) {
    this.resumeForm = this.fb.group({
      templateName: ['', Validators.required],
      description: ['', Validators.required],
      version: ['', Validators.required],
    });

    this.id = parseInt(this.route.snapshot.paramMap.get('id')!);
  }

  async ngOnInit() {
    if (this.id) { // edit case
      this.action = 'Edit';
      this.resume = await this.resumeService.getResume(this.id);
      this.resumeForm.patchValue({
        templateName: this.resume.templateName,
        description: this.resume.description,
        version: this.resume.version,
      });        
    }
  }

  // TODO: add ENTER later : avoid it's calling twice, make check submit validations of mandatory fields
  // TODO: ENTER ==> (keydown.enter)="addEditResume()"
  async addEditResume() {
    // check for new resume
    if (!this.resume) {
      this.resume = {
        id: 0,
        templateName: this.resumeForm.get('templateName')?.value,
        description: this.resumeForm.get('description')?.value,
        version: this.resumeForm.get('version')?.value,
      }
    } else { // update
      this.resume.templateName = this.resumeForm.get('templateName')?.value;
      this.resume.description = this.resumeForm.get('description')?.value;
      this.resume.version = this.resumeForm.get('version')?.value;
    }

    let newResume = await this.resumeService.upsertResume(this.resume);

    this.router.navigate(['/resumes']);
    console.log('upserted:' , newResume)
  }
}
