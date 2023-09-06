import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobOpportunityFormComponent } from './job-opportunity-form.component';

describe('JobOpportunityFormComponent', () => {
  let component: JobOpportunityFormComponent;
  let fixture: ComponentFixture<JobOpportunityFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobOpportunityFormComponent]
    });
    fixture = TestBed.createComponent(JobOpportunityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
