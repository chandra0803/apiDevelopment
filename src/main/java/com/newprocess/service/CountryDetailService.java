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

    public ResponseMessage getById(Long id) {
        Optional<CountryDetails> countryDetails = countryDetailsRepository.findById(id);
        if (countryDetails.isEmpty()) {
            return AppUtility.constructFailure("country Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(countryDetails, id);
        }
    }

    public ResponseMessage save(CountryDetails inputCountryDetails) {
        log.info("Request:: Country Details :{}", inputCountryDetails);
        return AppUtility.saveOrUpdateDomain(inputCountryDetails.getCountryCountryId(),inputCountryDetails, countryDetailsRepository);
   }

    public ResponseMessage delete(Long countryDetailsId) throws ResourceNotFoundException {

        Optional<CountryDetails> countryDetails = countryDetailsRepository.findById(countryDetailsId);
        if (countryDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Country Details", String.valueOf(countryDetailsId));
        }
        if (!countryDetails.isEmpty()) {
            log.info("Going to delete for {}", countryDetails.get());
            AppUtility.constructSuccess("Successfully deleted", countryDetails.get().getCountryCountryId());

            countryDetailsRepository.delete(countryDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}