package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.RateDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Chandra on 1/10/17.
 */
public interface RateDetailsService {

    public List<RateDetails> listAll();

    public RateDetails getById(Long id);

    public RateDetails save(RateDetails rateDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
