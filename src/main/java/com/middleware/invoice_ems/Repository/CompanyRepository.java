package com.middleware.invoice_ems.Repository;

import com.middleware.invoice_ems.Entity.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyDetails, Integer> {
}
