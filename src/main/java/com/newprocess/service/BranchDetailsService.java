package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.BranchDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface BranchDetailsService {

    public List<BranchDetails> listAll();

    public BranchDetails getById(Long id);

    public BranchDetails save(BranchDetails branchDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
