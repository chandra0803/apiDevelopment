package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.RateDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.RateDetailsRepository;
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
public class RateDetailService implements RateDetailsService {

    private final RateDetailsRepository rateDetailsRepository;

    public RateDetailService(RateDetailsRepository rateDetailsRepository) {
        this.rateDetailsRepository = rateDetailsRepository;
    }

    @Override
    public List<RateDetails> listAll() {
        List<RateDetails> rateDetails = new ArrayList<>();
        rateDetailsRepository.findAll().forEach(rateDetails::add);
        return rateDetails;
    }

    public ResponseMessage getById(Long id) {
        Optional<RateDetails> rateDetails = rateDetailsRepository.findById(id);
        if (rateDetails.isEmpty()) {
            return AppUtility.constructFailure("Rate Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(rateDetails, id);
        }
    }

    public ResponseMessage save(RateDetails inputRateDetails) {
        log.info("Request:: RateDetails Details :{}", inputRateDetails);
        return  AppUtility.saveOrUpdateDomain(inputRateDetails.getRateId().longValue(),inputRateDetails, rateDetailsRepository);
    }

    public ResponseMessage delete(Long rateDetailsId) throws ResourceNotFoundException {

        Optional<RateDetails> rateDetails = rateDetailsRepository.findById(rateDetailsId);
        if (rateDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Rate Details", String.valueOf(rateDetailsId));
        }
        if (!rateDetails.isEmpty()) {
            log.info("Going to delete for {}", rateDetails.get());
            AppUtility.constructSuccess("Successfully deleted", rateDetails.get().getRateId().longValue());
            rateDetailsRepository.delete(rateDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}