package com.rods.jobtracking.controller;

import com.rods.jobtracking.dto.CompanyDto;
import com.rods.jobtracking.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto createdCompany = companyService.createCompany(companyDto);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Long id) {
        CompanyDto company = companyService.getCompanyById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") Long id,
                                                    @RequestBody CompanyDto companyDto) {
        CompanyDto company = companyService.updateCompany(id, companyDto);
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        String returnMessage = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"message\": \"Company deleted successfully!\"\n" +
                "}";
        return ResponseEntity.ok(returnMessage);
    }
}
