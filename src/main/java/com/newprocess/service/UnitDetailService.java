package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.UnitDetails;
import com.newprocess.repositories.UnitDetailsRepository;
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
public class UnitDetailService implements UnitDetailsService {

    private final UnitDetailsRepository unitDetailsRepository;

    public UnitDetailService(UnitDetailsRepository unitDetailsRepository) {
        this.unitDetailsRepository = unitDetailsRepository;
    }

    @Override
    public List<UnitDetails> listAll() {
        List<UnitDetails> unitDetails = new ArrayList<>();
        unitDetailsRepository.findAll().forEach(unitDetails::add);
        return unitDetails;
    }

    public ResponseMessage getById(Long id) {
        Optional<UnitDetails> unitDetails = unitDetailsRepository.findById(id);
        if (unitDetails.isEmpty()) {
            return AppUtility.constructFailure("Unit Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(unitDetails, id);
        }
    }

    public ResponseMessage save(UnitDetails inputUnitDetails) {
        log.info("Request:: UnitDetails Details :{}", inputUnitDetails);
        return  AppUtility.saveOrUpdateDomain(inputUnitDetails.getUnitId(),inputUnitDetails, unitDetailsRepository);
    }

    public ResponseMessage delete(Long unitDetailsId) throws ResourceNotFoundException {

        Optional<UnitDetails> unitDetails = unitDetailsRepository.findById(unitDetailsId);
        if (unitDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Unit Details", String.valueOf(unitDetailsId));
        }
        if (!unitDetails.isEmpty()) {
            log.info("Going to delete for {}", unitDetails.get());
            AppUtility.constructSuccess("Successfully deleted", unitDetails.get().getUnitId());
            unitDetailsRepository.delete(unitDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}