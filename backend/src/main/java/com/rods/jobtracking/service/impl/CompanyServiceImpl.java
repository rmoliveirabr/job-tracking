package com.rods.jobtracking.service.impl;

import com.rods.jobtracking.dto.CompanyDto;
import com.rods.jobtracking.entity.Company;
import com.rods.jobtracking.exception.ResourceNotFoundException;
import com.rods.jobtracking.mapper.CompanyMapper;
import com.rods.jobtracking.repository.CompanyRepository;
import com.rods.jobtracking.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = CompanyMapper.mapToCompany(companyDto);
        Company savedCompany = companyRepository.save(company);

        return CompanyMapper.mapToCompanyDto(savedCompany);
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDto> companyDtos = companies.stream()
                .map((company) -> CompanyMapper.mapToCompanyDto(company))
                .collect(Collectors.toList());
        return companyDtos;
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with id '" + id + "' not found"));
        return CompanyMapper.mapToCompanyDto(company);
    }

    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with id '" + id + "' not found"));

        company = CompanyMapper.mapToCompany(companyDto, company);
        Company updatedCompany = companyRepository.save(company);
        return CompanyMapper.mapToCompanyDto(updatedCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company with id '" + id + "' not found"));

        companyRepository.deleteById(id);
    }
}
