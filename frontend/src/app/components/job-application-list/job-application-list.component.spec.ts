import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobApplicationListComponent } from './job-application-list.component';

describe('JobApplicationListComponent', () => {
  let component: JobApplicationListComponent;
  let fixture: ComponentFixture<JobApplicationListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobApplicationListComponent]
    });
    fixture = TestBed.createComponent(JobApplicationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
