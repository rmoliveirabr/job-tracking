import { Injectable } from '@angular/core';
import { Resume } from 'src/app/model/Resume';
import { RestService} from './restService'

@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  restService: RestService<Resume> = new RestService<Resume>("http://localhost:8080/api/resumes");

  async getResumes(): Promise<Resume[]> {
    const resumes:Resume[] = await this.restService.getAll();
    console.log('Retrieved resumes:', resumes);

    return resumes;
  }

  async getResume(id:number): Promise<Resume> {
    const resume:Resume = await this.restService.getById(id);
    console.log('Getting resume for:', id, resume);

    return resume;
  }

  async deleteResume(id:number): Promise<boolean> {
    const result = await this.restService.delete(id);
    console.log('Deleted resume for:', id);

    return result;
  }

  async upsertResume(resume:Resume): Promise<Resume> {
    let newResume: Resume;

    if (resume.id === 0) {
      newResume = await this.restService.create(resume);
    } else {
      newResume = await this.restService.update(resume.id, resume);
    }

    console.log('Upserted resume for:', newResume);
    
    return newResume;
  }
}
