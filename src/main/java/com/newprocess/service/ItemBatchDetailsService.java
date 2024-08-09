package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ItemBatchDetails;
import com.newprocess.api.ResponseMessage;

import java.util.List;

/**
 * Created by Chandra on 1/10/17.
 */
public interface ItemBatchDetailsService {

    public List<ItemBatchDetails> listAll();

    public ResponseMessage getById(Long id);

    public ResponseMessage save(ItemBatchDetails itemBatchDetails);

    public ResponseMessage delete(Long id) throws ResourceNotFoundException;
}
