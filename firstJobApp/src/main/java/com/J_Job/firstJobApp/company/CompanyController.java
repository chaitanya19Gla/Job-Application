package com.J_Job.firstJobApp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){

        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(company,id);
        return new ResponseEntity<>("Company is Updated", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
//        if (company.getName() == null || company.getId() == null) {
//            return new ResponseEntity<>("Opps! you have made a mistake", HttpStatus.BAD_REQUEST);
//        }
        companyService.createCompany(company);
        return new ResponseEntity<>("Company is Created", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable  Long id){
        boolean deleted = companyService.DeleteCompanyById(id);
        if (deleted){
            return new ResponseEntity<>("The company is deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Opps You have made a mistake",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company c = companyService.getCompanyById(id);
        if (c == null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(c,HttpStatus.OK);
        }
    }
}
