import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from 'src/app/model/Company';
import { CompanyService } from 'src/app/services/company.service.service';

@Component({
  selector: 'app-company-view',
  templateUrl: './company-view.component.html',
  styleUrls: ['./company-view.component.css']
})
export class CompanyViewComponent implements OnInit {
  public company?:Company;

  public id:number;

  constructor(private companyService:CompanyService, private route: ActivatedRoute) {
    this.id = parseInt(this.route.snapshot.paramMap.get('id')!);
  }

  async ngOnInit() {
    this.company = await this.companyService.getCompany(this.id);
  }
}
