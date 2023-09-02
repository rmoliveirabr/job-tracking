import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobRoleViewComponent } from './job-role-view.component';

describe('JobRoleViewComponent', () => {
  let component: JobRoleViewComponent;
  let fixture: ComponentFixture<JobRoleViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobRoleViewComponent]
    });
    fixture = TestBed.createComponent(JobRoleViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
