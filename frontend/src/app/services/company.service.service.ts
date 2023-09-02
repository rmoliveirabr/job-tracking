import { Injectable } from '@angular/core';
import { Company } from '../model/Company';
import { RestService } from './restService';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  restService: RestService<Company> = new RestService<Company>("http://localhost:8080/api/companies");

  async getCompanies(): Promise<Company[]> {
    const companies:Company[] = await this.restService.getAll();
    console.log('Retrieved companies:', companies);

    return companies;
  }

  async getCompany(id:number): Promise<Company> {
    const company:Company = await this.restService.getById(id);
    console.log('Getting company for:', id, company);

    return company;
  }

  async deleteCompany(id:number): Promise<boolean> {
    const result = await this.restService.delete(id);    
    console.log('Deleted company for:', id);

    return result;
  }

  async upsertCompany(company:Company): Promise<number> {
    if (company.id === 0) {
      await this.restService.create(company);    
    } else {
      await this.restService.update(company.id, company);    
    }

    console.log('Upserted company for:', company.id);
    
    return company.id;
  }
}
