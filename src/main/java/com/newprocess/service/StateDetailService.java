package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.StateDetails;
import com.newprocess.repositories.StateDetailsRepository;
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
public class StateDetailService implements StateDetailsService {

    private final StateDetailsRepository stateDetailsRepository;

    public StateDetailService(StateDetailsRepository stateDetailsRepository) {
        this.stateDetailsRepository = stateDetailsRepository;
    }

    @Override
    public List<StateDetails> listAll() {
        List<StateDetails> stateDetails = new ArrayList<>();
        stateDetailsRepository.findAll().forEach(stateDetails::add);
        return stateDetails;
    }

    @Override
    public StateDetails getById(Long id) {
        return stateDetailsRepository.findById(id).orElse(null);
    }


    public StateDetails save(StateDetails inputStateDetails) {
        stateDetailsRepository.save(inputStateDetails);
        log.info("updated stateDetails :{}", inputStateDetails);
        return inputStateDetails;
    }

    public ResponseMessage delete(Long stateDetailsId) throws ResourceNotFoundException {

        Optional<StateDetails> stateDetails = stateDetailsRepository.findById(stateDetailsId);
        if (stateDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("State Details", String.valueOf(stateDetailsId));
        }
        if (!stateDetails.isEmpty()) {
            log.info("Going to delete for {}", stateDetails.get());
            stateDetailsRepository.delete(stateDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }
}