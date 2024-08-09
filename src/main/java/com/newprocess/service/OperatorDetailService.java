package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.OperatorDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.OperatorDetailsRepository;
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
public class OperatorDetailService implements OperatorDetailsService {
    private final OperatorDetailsRepository operatorDetailsRepository;

    public OperatorDetailService(OperatorDetailsRepository operatorDetailsRepository) {
        this.operatorDetailsRepository = operatorDetailsRepository;
    }

    @Override
    public List<OperatorDetails> listAll() {
        List<OperatorDetails> operatorDetails = new ArrayList<>();
        operatorDetailsRepository.findAll().forEach(operatorDetails::add);
        return operatorDetails;
    }

    public ResponseMessage getById(Long id) {
        Optional<OperatorDetails> operatorDetails = operatorDetailsRepository.findById(id);
        if (operatorDetails.isEmpty()) {
            return AppUtility.constructFailure("operator Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(operatorDetails, id);
        }
    }

    public ResponseMessage save(OperatorDetails operatorDetails) {
        log.info("Request:: operator Details :{}", operatorDetails);
        return AppUtility.saveOrUpdateDomain(operatorDetails.getOperatorId(),operatorDetails, operatorDetailsRepository);
     }


    public ResponseMessage delete(Long operatorDetailsId) throws ResourceNotFoundException {

        Optional<OperatorDetails> operatorDetails = operatorDetailsRepository.findById(operatorDetailsId);
        if (operatorDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Operator Details", String.valueOf(operatorDetailsId));
        }
        if (!operatorDetails.isEmpty()) {
            log.info("Going to delete for {}", operatorDetails.get());
            AppUtility.constructSuccess("Successfully deleted", operatorDetails.get().getOperatorId());
            operatorDetailsRepository.delete(operatorDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}