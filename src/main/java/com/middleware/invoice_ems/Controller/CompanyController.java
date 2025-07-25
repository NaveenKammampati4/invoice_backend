package com.middleware.invoice_ems.Controller;

import com.middleware.invoice_ems.Entity.CompanyDetails;
import com.middleware.invoice_ems.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companyDetails")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<?> saveCompany(@RequestBody CompanyDetails companyDetails) {
        CompanyDetails saveDetails = companyService.saveCompanyDetails(companyDetails);
        return ResponseEntity.ok(saveDetails);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDetails>> getAllCompanyDetails() {
        List<CompanyDetails> allCompanyDetails = companyService.getAllCompanyDetails();
        return ResponseEntity.ok(allCompanyDetails);
    }

    @GetMapping("/getData/{id}")
    public ResponseEntity<CompanyDetails> getCompanyDetails(@PathVariable int id) {
        CompanyDetails companyDetails = companyService.getCompanyDetails(id);
        return ResponseEntity.ok(companyDetails);
    }


    @GetMapping("/getByCountry/{country}")
    public ResponseEntity<List<CompanyDetails>> findByCountry(@PathVariable String country){
        List<CompanyDetails> companyDetails = companyService.findByCountry(country);
        return ResponseEntity.ok(companyDetails);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<CompanyDetails> updateById(@PathVariable int id, @RequestBody CompanyDetails companyDetails){
        CompanyDetails updateDetails = companyService.updateCompanyById(id, companyDetails);
        return ResponseEntity.ok(updateDetails);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        String companyDetails = companyService.deleteById(id);
        return ResponseEntity.ok(companyDetails);
    }
}
