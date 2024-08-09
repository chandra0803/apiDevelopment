package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ItemDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.ItemDetailsRepository;
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
public class ItemDetailService implements ItemDetailsService {

    private final ItemDetailsRepository itemDetailsRepository;

    public ItemDetailService(ItemDetailsRepository itemDetailsRepository) {
        this.itemDetailsRepository = itemDetailsRepository;
    }

    @Override
    public List<ItemDetails> listAll() {
        List<ItemDetails> itemDetails = new ArrayList<>();
        itemDetailsRepository.findAll().forEach(itemDetails::add);
        return itemDetails;
    }

    public ResponseMessage getById(Long id) {
        Optional<ItemDetails> itemDetails = itemDetailsRepository.findById(id);
        if (itemDetails.isEmpty()) {
            return AppUtility.constructFailure("Item Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(itemDetails, id);
        }
    }

    public ResponseMessage save(ItemDetails inputItemDetails) {
        log.info("Request:: ItemDetails Details :{}", inputItemDetails);
        return AppUtility.saveOrUpdateDomain(inputItemDetails.getItemId(),inputItemDetails, itemDetailsRepository);
       }


    public ResponseMessage delete(Long itemDetailsId) throws ResourceNotFoundException {

        Optional<ItemDetails> itemDetails = itemDetailsRepository.findById(itemDetailsId);
        if (itemDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Item Details", String.valueOf(itemDetailsId));
        }
        if (!itemDetails.isEmpty()) {
            log.info("Going to delete for {}", itemDetails.get());
            AppUtility.constructSuccess("Successfully deleted", itemDetails.get().getItemId());
            itemDetailsRepository.delete(itemDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }
}
