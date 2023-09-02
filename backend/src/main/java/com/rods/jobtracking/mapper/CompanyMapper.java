package com.rods.jobtracking.mapper;

import com.rods.jobtracking.dto.CompanyDto;
import com.rods.jobtracking.entity.Company;

public class CompanyMapper {
    public static CompanyDto mapToCompanyDto(Company company) {
        return new CompanyDto(company.getId(), company.getName(), company.getUrl());
    }

    public static Company mapToCompany(CompanyDto companyDto) {
        Company company = new Company();
        return mapToCompany(companyDto, company);
    }

    public static Company mapToCompany(CompanyDto companyDto, Company company) {
        company.setName(companyDto.getName());
        company.setUrl(companyDto.getUrl());
        return company;
    }
}
