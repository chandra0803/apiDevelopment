package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ItemDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface ItemDetailsService {

    public List<ItemDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(ItemDetails itemDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
