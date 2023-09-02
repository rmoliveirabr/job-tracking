import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from 'src/app/model/Company';
import { CompanyService } from 'src/app/services/company.service.service';

@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.css']
})
export class CompanyFormComponent implements OnInit {
  public companyForm: FormGroup;
  public action = 'Add';
  
  public id: number;
  public company?: Company;

  constructor(private companyService:CompanyService, private route: ActivatedRoute, 
    private router: Router, private fb: FormBuilder,
    private elementRef: ElementRef
    ) {
      this.companyForm  = this.fb.group({
        name: ['', Validators.required],
        url: [''],
      });

      this.id = parseInt(this.route.snapshot.paramMap.get('id')!);
  }

  async ngOnInit() {
    if (this.id) { // edit case
      this.action = 'Edit';
      this.company = await this.companyService.getCompany(this.id);
      this.companyForm.patchValue({
        name: this.company.name,
        url: this.company.url
      });
    }
  }

  async addEditCompany() {
    // check for new company
    if (!this.company) {
      this.company = {
        id: 0,
        name: this.companyForm.get('name')?.value,
        url: this.companyForm.get('url')?.value,
      }
    } else { // update
      this.company.name = this.companyForm.get('name')?.value;
      this.company.url = this.companyForm.get('url')?.value;
    }

    let id = await this.companyService.upsertCompany(this.company);

    this.router.navigate(['/companies']);
    console.log('upserted:' , this.company)
  }
}
