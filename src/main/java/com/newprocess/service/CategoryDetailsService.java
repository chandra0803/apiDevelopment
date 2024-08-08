package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CategoryDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Chandra on 1/10/17.
 */
public interface CategoryDetailsService {

    public List<CategoryDetails> listAll();

    public CategoryDetails getById(Long id);

    public CategoryDetails save(CategoryDetails categoryDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
