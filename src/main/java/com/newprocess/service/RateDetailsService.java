package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.RateDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface RateDetailsService {

    public List<RateDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(RateDetails rateDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
