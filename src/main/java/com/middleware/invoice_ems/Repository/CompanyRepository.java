package com.middleware.invoice_ems.Repository;

import com.middleware.invoice_ems.Entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDetails, Integer> {

   List <CompanyDetails> findByCountry(String country);
}
