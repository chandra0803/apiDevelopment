package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CompanyDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Chandra on 1/10/17.
 */
public interface CompanyDetailsService {

    public List<CompanyDetails> listAll();

    public CompanyDetails getById(Long id);

    public CompanyDetails save(CompanyDetails companyDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
