package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CompanyDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.CompanyDetailsRepository;
import com.newprocess.util.AppUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Chandra on 1/10/17.
 */
@Service
@Slf4j
public class CompanyDetailService implements CompanyDetailsService {

    private final CompanyDetailsRepository companyDetailsRepository;

    public CompanyDetailService(CompanyDetailsRepository companyDetailsRepository) {
        this.companyDetailsRepository = companyDetailsRepository;
    }

    @Override
    public List<CompanyDetails> listAll() {
        List<CompanyDetails> companyDetails = new ArrayList<>();
        companyDetailsRepository.findAll().forEach(companyDetails::add);
        return companyDetails;
    }

    @Override
    public CompanyDetails getById(Long id) {
        return companyDetailsRepository.findById(id).orElse(null);
    }


    public CompanyDetails save(CompanyDetails inputCompanyDetails) {
        companyDetailsRepository.save(inputCompanyDetails);
        log.info("updated companyDetails :{}", inputCompanyDetails);
        return inputCompanyDetails;
    }

    public ResponseMessage delete(Long companyDetailsId) throws ResourceNotFoundException {

        Optional<CompanyDetails> companyDetails = companyDetailsRepository.findById(companyDetailsId);
        if (companyDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Company Details", String.valueOf(companyDetailsId));
        }
        if (!companyDetails.isEmpty()) {
            log.info("Going to delete for {}", companyDetails.get());
            companyDetailsRepository.delete(companyDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }
}