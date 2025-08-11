package com.J_Job.firstJobApp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company,Long id);

    void createCompany(Company company);

    boolean DeleteCompanyById(Long id);

    Company getCompanyById(Long id);
}
