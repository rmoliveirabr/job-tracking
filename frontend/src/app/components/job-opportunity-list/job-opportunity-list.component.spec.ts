import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobOpportunityListComponent } from './job-opportunity-list.component';

describe('JobOpportunityListComponent', () => {
  let component: JobOpportunityListComponent;
  let fixture: ComponentFixture<JobOpportunityListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobOpportunityListComponent]
    });
    fixture = TestBed.createComponent(JobOpportunityListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
