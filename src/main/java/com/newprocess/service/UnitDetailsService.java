package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.UnitDetails;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface UnitDetailsService {

    public List<UnitDetails> listAll();

    public UnitDetails getById(Long id);

    public UnitDetails save(UnitDetails unitDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
