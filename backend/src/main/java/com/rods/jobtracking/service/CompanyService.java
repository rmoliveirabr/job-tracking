package com.rods.jobtracking.service;

import com.rods.jobtracking.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto createCompany(CompanyDto companyDto);
    List<CompanyDto> getAllCompanies();
    CompanyDto getCompanyById(Long id);
    CompanyDto updateCompany(Long id, CompanyDto companyDto);
    void deleteCompany(Long id);

}
