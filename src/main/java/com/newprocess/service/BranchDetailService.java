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
        List<BranchDetails> branchDetails = new ArrayList<>();
        branchDetailsRepository.findAll().forEach(branchDetails::add);
        return branchDetails;
    }

    @Override
    public BranchDetails getById(Long id) {
        return branchDetailsRepository.findById(id).orElse(null);
    }


    public BranchDetails save(BranchDetails inputBranchDetails) {
        branchDetailsRepository.save(inputBranchDetails);
        log.info("updated branchDetails :{}", inputBranchDetails);
        return inputBranchDetails;
    }

    public ResponseMessage delete(Long branchDetailsId) throws ResourceNotFoundException {

        Optional<BranchDetails> branchDetails = branchDetailsRepository.findById(branchDetailsId);
        if (branchDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Branch Details", String.valueOf(branchDetailsId));
        }
        if (!branchDetails.isEmpty()) {
            log.info("Going to delete for {}", branchDetails.get());
            branchDetailsRepository.delete(branchDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }

}
