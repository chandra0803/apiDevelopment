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

    @Override
    public OperatorDetails getById(Long id) {
        return operatorDetailsRepository.findById(id).orElse(null);
    }


    public OperatorDetails save(OperatorDetails inputOperatorDetails) {
        operatorDetailsRepository.save(inputOperatorDetails);
        log.info("updated operatorDetails :{}", inputOperatorDetails);
        return inputOperatorDetails;
    }

    public ResponseMessage delete(Long operatorDetailsId) throws ResourceNotFoundException {

        Optional<OperatorDetails> operatorDetails = operatorDetailsRepository.findById(operatorDetailsId);
        if (operatorDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Operator Details", String.valueOf(operatorDetailsId));
        }
        if (!operatorDetails.isEmpty()) {
            log.info("Going to delete for {}", operatorDetails.get());
            operatorDetailsRepository.delete(operatorDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }
}