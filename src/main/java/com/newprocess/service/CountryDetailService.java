package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CountryDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.CountryDetailsRepository;
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
public class CountryDetailService implements CountryDetailsService {
    private final CountryDetailsRepository countryDetailsRepository;

    public CountryDetailService(CountryDetailsRepository countryDetailsRepository) {
        this.countryDetailsRepository = countryDetailsRepository;
    }

    @Override
    public List<CountryDetails> listAll() {
        List<CountryDetails> countryDetails = new ArrayList<>();
        countryDetailsRepository.findAll().forEach(countryDetails::add);
        return countryDetails;
    }

    @Override
    public CountryDetails getById(Long id) {
        return countryDetailsRepository.findById(id).orElse(null);
    }


    public CountryDetails save(CountryDetails inputCountryDetails) {
        countryDetailsRepository.save(inputCountryDetails);
        log.info("updated countryDetails :{}", inputCountryDetails);
        return inputCountryDetails;
    }

    public ResponseMessage delete(Long countryDetailsId) throws ResourceNotFoundException {

        Optional<CountryDetails> countryDetails = countryDetailsRepository.findById(countryDetailsId);
        if (countryDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Country Details", String.valueOf(countryDetailsId));
        }
        if (!countryDetails.isEmpty()) {
            log.info("Going to delete for {}", countryDetails.get());
            countryDetailsRepository.delete(countryDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }
}