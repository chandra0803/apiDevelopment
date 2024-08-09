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

    public ResponseMessage getById(Long id) {
        Optional<StateDetails> stateDetails = stateDetailsRepository.findById(id);
        if (stateDetails.isEmpty()) {
            return AppUtility.constructFailure("State Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(stateDetails, id);
        }
    }

    public ResponseMessage save(StateDetails inputStateDetails) {
        log.info("Request:: StateDetails Details :{}", inputStateDetails);
        return AppUtility.saveOrUpdateDomain(inputStateDetails.getStateId(),inputStateDetails, stateDetailsRepository);
     }

    public ResponseMessage delete(Long stateDetailsId) throws ResourceNotFoundException {

        Optional<StateDetails> stateDetails = stateDetailsRepository.findById(stateDetailsId);
        if (stateDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("State Details", String.valueOf(stateDetailsId));
        }
        if (!stateDetails.isEmpty()) {
            log.info("Going to delete for {}", stateDetails.get());
            AppUtility.constructSuccess("Successfully deleted", stateDetails.get().getStateId());
            stateDetailsRepository.delete(stateDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}