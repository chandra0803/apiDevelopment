package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CountryDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Chandra on 1/10/17.
 */
public interface CountryDetailsService {

    public List<CountryDetails> listAll();

    public CountryDetails getById(Long id);

    public CountryDetails save(CountryDetails countryDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
