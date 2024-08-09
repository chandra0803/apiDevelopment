package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CompanyDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface CompanyDetailsService {

    public List<CompanyDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(CompanyDetails companyDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
