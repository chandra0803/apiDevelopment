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

    public ResponseMessage getById(Long id);

    public ResponseMessage save(UnitDetails unitDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
