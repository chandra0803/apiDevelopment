package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.OperatorDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface OperatorDetailsService {

    public List<OperatorDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(OperatorDetails operatorDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
