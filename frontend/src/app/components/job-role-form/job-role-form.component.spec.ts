import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobRoleFormComponent } from './job-role-form.component';

describe('JobRoleFormComponent', () => {
  let component: JobRoleFormComponent;
  let fixture: ComponentFixture<JobRoleFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobRoleFormComponent]
    });
    fixture = TestBed.createComponent(JobRoleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
