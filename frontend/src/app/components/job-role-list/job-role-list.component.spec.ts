import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobRoleListComponent } from './job-role-list.component';

describe('JobRoleListComponent', () => {
  let component: JobRoleListComponent;
  let fixture: ComponentFixture<JobRoleListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobRoleListComponent]
    });
    fixture = TestBed.createComponent(JobRoleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
