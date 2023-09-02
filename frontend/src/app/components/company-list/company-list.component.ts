import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/model/Company';
import { CompanyService } from 'src/app/services/company.service.service';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  companies: Company[] = [];

  constructor(private companyService: CompanyService) { }

  async ngOnInit() {
    this.companies = await this.companyService.getCompanies();
    console.log('Returned companies:',this.companies);
  }

  async deleteCompany(id:number) {
    let result:boolean = await this.companyService.deleteCompany(id);
    if (result)
      this.companies = await this.companyService.getCompanies();
  }
}
