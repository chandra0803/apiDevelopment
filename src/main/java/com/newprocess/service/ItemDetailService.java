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

    @Override
    public ItemDetails getById(Long id) {
        return itemDetailsRepository.findById(id).orElse(null);
    }


    public ItemDetails save(ItemDetails inputItemDetails) {
        itemDetailsRepository.save(inputItemDetails);
        log.info("updated itemDetails :{}", inputItemDetails);
        return inputItemDetails;
    }

    public ResponseMessage delete(Long itemDetailsId) throws ResourceNotFoundException {

        Optional<ItemDetails> itemDetails = itemDetailsRepository.findById(itemDetailsId);
        if (itemDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Item Details", String.valueOf(itemDetailsId));
        }
        if (!itemDetails.isEmpty()) {
            log.info("Going to delete for {}", itemDetails.get());
            itemDetailsRepository.delete(itemDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().details("Successfully deleted").build();
        }
        return null;
    }
}
