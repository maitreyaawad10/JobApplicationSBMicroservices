package com.maitreya.companyms.company;

import java.util.List;

public interface CompanyService {
    void addCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    boolean updateCompanyById(Long id, Company updatedCompany);

    boolean deleteCompanyById(Long id);
}
