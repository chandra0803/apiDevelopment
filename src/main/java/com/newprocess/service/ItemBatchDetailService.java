package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ItemBatchDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.ItemBatchDetailsRepository;
import com.newprocess.util.AppUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Chandra on 1/10/17.
 */
@Service
@Slf4j
public class ItemBatchDetailService implements ItemBatchDetailsService {

    private final ItemBatchDetailsRepository itemBatchDetailsRepository;

    public ItemBatchDetailService(ItemBatchDetailsRepository itemBatchDetailsRepository) {
        this.itemBatchDetailsRepository = itemBatchDetailsRepository;
    }

    @Override
    public List<ItemBatchDetails> listAll() {
        List<ItemBatchDetails> itemBatchDetails = new ArrayList<>();
        itemBatchDetailsRepository.findAll().forEach(itemBatchDetails::add);
        return itemBatchDetails;
    }

    public ResponseMessage getById(Long id) {
        Optional<ItemBatchDetails> itemBatchDetails = itemBatchDetailsRepository.findById(id);
        if (itemBatchDetails.isEmpty()) {
            return AppUtility.constructFailure("itemBatch Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(itemBatchDetails, id);
        }
    }

    public ResponseMessage save(ItemBatchDetails inputItemBatchDetails) {
        log.info("Request:: ItemBatchDetails Details :{}", inputItemBatchDetails);
        return  AppUtility.saveOrUpdateDomain(inputItemBatchDetails.getItemBatchId(),inputItemBatchDetails, itemBatchDetailsRepository);
     }


    public ResponseMessage delete(Long itemBatchDetailsId) throws ResourceNotFoundException {

        Optional<ItemBatchDetails> itemBatchDetails = itemBatchDetailsRepository.findById(itemBatchDetailsId);
        if (itemBatchDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("ItemBatch Details", String.valueOf(itemBatchDetailsId));
        }
        if (!itemBatchDetails.isEmpty()) {
            log.info("Going to delete for {}", itemBatchDetails.get());
            AppUtility.constructSuccess("Successfully deleted", itemBatchDetails.get().getItemBatchId());
            itemBatchDetailsRepository.delete(itemBatchDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}