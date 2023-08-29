import { Component } from '@angular/core';
import { Resume } from 'src/app/model/Resume';
import { ResumeService } from 'src/app/services/resume.service';

@Component({
  selector: 'app-resume-list',
  templateUrl: './resume-list.component.html',
  styleUrls: ['./resume-list.component.css']
})
export class ResumeListComponent {
  resumes: Resume[] = [];

  constructor(private resumeService: ResumeService) { }

  async ngOnInit() {
    this.resumes = await this.resumeService.getResumes();
    console.log('Returned resumes:',this.resumes);
  }

  async deleteResume(id:number) {
    let result:boolean = await this.resumeService.deleteResume(id);
    if (result)
      this.resumes = await this.resumeService.getResumes();
  }
}
