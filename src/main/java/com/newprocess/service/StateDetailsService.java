package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.StateDetails;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface StateDetailsService {

    public List<StateDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(StateDetails stateDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
