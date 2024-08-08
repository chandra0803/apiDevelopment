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

    @Override
    public UnitDetails getById(Long id) {
        return unitDetailsRepository.findById(id).orElse(null);
    }


    public UnitDetails save(UnitDetails inputUnitDetails) {
        unitDetailsRepository.save(inputUnitDetails);
        log.info("updated unitDetails :{}", inputUnitDetails);
        return inputUnitDetails;
    }

    public ResponseMessage delete(Long unitDetailsId) throws ResourceNotFoundException {

        Optional<UnitDetails> unitDetails = unitDetailsRepository.findById(unitDetailsId);
        if (unitDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Unit Details", String.valueOf(unitDetailsId));
        }
        if (!unitDetails.isEmpty()) {
            log.info("Going to delete for {}", unitDetails.get());
            unitDetailsRepository.delete(unitDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }
}