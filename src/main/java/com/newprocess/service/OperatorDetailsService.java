package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.OperatorDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Chandra on 1/10/17.
 */
public interface OperatorDetailsService {

    public List<OperatorDetails> listAll();

    public OperatorDetails getById(Long id);

    public OperatorDetails save(OperatorDetails operatorDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
