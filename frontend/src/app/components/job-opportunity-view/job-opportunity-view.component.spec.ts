import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobOpportunityViewComponent } from './job-opportunity-view.component';

describe('JobOpportunityViewComponent', () => {
  let component: JobOpportunityViewComponent;
  let fixture: ComponentFixture<JobOpportunityViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobOpportunityViewComponent]
    });
    fixture = TestBed.createComponent(JobOpportunityViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
