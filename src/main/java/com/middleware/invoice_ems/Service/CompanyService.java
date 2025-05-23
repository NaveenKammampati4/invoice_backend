package com.middleware.invoice_ems.Service;

import com.middleware.invoice_ems.Entity.CompanyDetails;
import com.middleware.invoice_ems.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDetails saveCompanyDetails(CompanyDetails companyDetails) {
        return companyRepository.save(companyDetails);
    }

    public List<CompanyDetails> getAllCompanyDetails() {
        return companyRepository.findAll();
    }

    public CompanyDetails getCompanyDetails(int id) {
        CompanyDetails companyDetails = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        return companyDetails;
    }
}
