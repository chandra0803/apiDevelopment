package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.BranchDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.BranchDetailsRepository;
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
public class BranchDetailService implements BranchDetailsService {
    private final BranchDetailsRepository branchDetailsRepository;

    public BranchDetailService(BranchDetailsRepository branchDetailsRepository) {
        this.branchDetailsRepository = branchDetailsRepository;
    }

    @Override
    public List<BranchDetails> listAll() {
        List<BranchDetails> branchDetailsList = new ArrayList<>();
        branchDetailsRepository.findAll().forEach(branchDetailsList::add);
        log.info("Total no of items:{}", branchDetailsList.size());
        return branchDetailsList;
    }

    @Override
    public ResponseMessage getById(Long id) {
        Optional<BranchDetails> branchDetails = branchDetailsRepository.findById(id);
        if (branchDetails.isEmpty()) {
            return AppUtility.constructFailure("Branch Details not found for this id " + id, id);

        } else {
            return AppUtility.constructSuccess(branchDetails, id);
        }
    }

    public ResponseMessage save(BranchDetails inputBranchDetails) {
        log.info("Request:: branchDetails :{}", inputBranchDetails);
        return  AppUtility.saveOrUpdateDomain(inputBranchDetails.getBranchId(),inputBranchDetails, branchDetailsRepository);
     }

    public ResponseMessage delete(Long branchDetailsId) throws ResourceNotFoundException {

        Optional<BranchDetails> branchDetails = branchDetailsRepository.findById(branchDetailsId);
        log.info("Deleting id:{}",branchDetailsId);
        if (branchDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Branch Details", String.valueOf(branchDetailsId));
        }
        if (!branchDetails.isEmpty()) {
            log.info("Going to delete for {}", branchDetails.get());
            branchDetailsRepository.delete(branchDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }

}
