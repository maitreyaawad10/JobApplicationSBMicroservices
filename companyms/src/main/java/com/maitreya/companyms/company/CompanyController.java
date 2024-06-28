package com.maitreya.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // ADD A COMPANY
    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
    }

    // GET ALL COMPANIES
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    // GET COMPANY BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null)
            return new ResponseEntity<>(company, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // UPDATE COMPANY BY ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean isCompanyUpdated = companyService.updateCompanyById(id, updatedCompany);
        if (isCompanyUpdated)
            return new ResponseEntity<>("Company updated successfully!", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE COMPANY BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean isCompanyDeleted = companyService.deleteCompanyById(id);
        if (isCompanyDeleted)
            return new ResponseEntity<>("Company deleted successfully!", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
