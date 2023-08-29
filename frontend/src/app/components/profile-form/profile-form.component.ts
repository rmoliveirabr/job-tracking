import { Component, ElementRef, OnInit } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Profile } from '../../model/Profile';
import { Resume } from '../../model/Resume';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.css']
})
export class ProfileFormComponent implements OnInit {
  public profileForm: FormGroup;
  public resumeForm: FormGroup;

  public action = 'Add';
  
  public id: number;
  public profile?: Profile;
  public resume: Resume = {
    id: 0,
    templateName: '',
    description: '',
    version: 0
  };
  public resumes: Resume[] = [];

  constructor(private profileService:ProfileService, private route: ActivatedRoute, 
              private router: Router, private fb: FormBuilder,
              private elementRef: ElementRef
              ) {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
    });

    this.resumeForm = this.fb.group({
      templateName: [''],
      description: [''],
      version: [''],
    });

    this.id = parseInt(this.route.snapshot.paramMap.get('id')!);
  }

  async ngOnInit() {
    if (this.id) { // edit case
      this.action = 'Edit';
      this.profile = await this.profileService.getProfile(this.id);
      this.profileForm.patchValue({
        name: this.profile.name,
        resumes: this.profile.resumes
      });
      this.resumes = this.profile.resumes || [];
    }
  }

  async addEditProfile() {
    // check for new profile
    if (!this.profile) {
      this.profile = {
        id: 0,
        name: this.profileForm.get('name')?.value,
        resumes: []
      }
    } else { // update
      this.profile.name = this.profileForm.get('name')?.value;
      this.profile.resumes = this.resumes;
    }

    let id = await this.profileService.upsertProfile(this.profile);

    this.router.navigate(['/profiles']);
    console.log('upserted:' , this.profile)
  }

  async addEditResume(id?:number) {
    if (id) {
      this.resume = this.resumes.filter(resume => resume.id === id)[0];

      this.resumeForm.patchValue({
        templateName: this.resume.templateName,
        description: this.resume.description,
        version: this.resume.version
      });
    } else {
      this.resume = {
        id:0,
        templateName: '',
        description: '',
        version: 0
      }

      this.resumeForm.patchValue({
        templateName: '',
        description: '',
        version: ''
      });
    }
    
    this.openModal();
  }

  async deleteResume(id:number) {
    this.resumes = this.resumes.filter(resume => resume.id != id);
  }

  async saveResume() {
    // update current resume
    if (!this.resume) return;

    this.resume.templateName = this.resumeForm.get('templateName')?.value;
    this.resume.description = this.resumeForm.get('description')?.value;
    this.resume.version = this.resumeForm.get('version')?.value;

    // if existing, update, otherwise push
    if (this.resume?.id != 0) {
      const index = this.resumes.findIndex(resume => resume.id === this.resume?.id);
      if (index !== -1) 
        this.resumes = this.resumes.map((resume, i) => (i === index) ? this.resume : resume);
    } else { // new resume
      //this.resume.id = Date.now().toString(),
      this.resumes.push(this.resume);
    }

    this.closeModal();
  }
 
  async openModal() {
    const modalElement: HTMLElement = this.elementRef.nativeElement.querySelector('#resumeModal');
    modalElement.style.display = 'block';
    modalElement.classList.add('show');
  }

  async closeModal() {
    const modalElement: HTMLElement = this.elementRef.nativeElement.querySelector('#resumeModal');
    modalElement.style.display = 'none';
    modalElement.classList.remove('show');
  }
}
