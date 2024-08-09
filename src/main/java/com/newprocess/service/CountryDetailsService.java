package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CountryDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface CountryDetailsService {

    public List<CountryDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(CountryDetails countryDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
