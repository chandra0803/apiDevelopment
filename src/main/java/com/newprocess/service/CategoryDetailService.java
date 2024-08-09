package com.newprocess.service;

import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CategoryDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.CategoryDetailsRepository;
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
public class CategoryDetailService implements CategoryDetailsService {

    private final CategoryDetailsRepository categoryDetailsRepository;

    public CategoryDetailService(CategoryDetailsRepository categoryDetailsRepository) {
        this.categoryDetailsRepository = categoryDetailsRepository;
    }

    @Override
    public List<CategoryDetails> listAll() {
        List<CategoryDetails> categoryDetails = new ArrayList<>();
        categoryDetailsRepository.findAll().forEach(categoryDetails::add);
        return categoryDetails;
    }

    public ResponseMessage getById(Long id) {
        Optional<CategoryDetails> categoryDetails = categoryDetailsRepository.findById(id);
        if (categoryDetails.isEmpty()) {
            return AppUtility.constructFailure("Category Details not found for this id " + id, id);
        } else {
            return AppUtility.constructSuccess(categoryDetails, id);
        }
    }

    public ResponseMessage save(CategoryDetails inputCategoryDetails) {
        log.info("Request:: CategoryDetails :{}", inputCategoryDetails);
       return AppUtility.saveOrUpdateDomain(inputCategoryDetails.getCategoryId(),inputCategoryDetails, categoryDetailsRepository);
    }

    public ResponseMessage delete(Long categoryDetailsId) throws ResourceNotFoundException {

        Optional<CategoryDetails> categoryDetails = categoryDetailsRepository.findById(categoryDetailsId);
        if (categoryDetails.isEmpty()) {
            return AppUtility.contructErrorMsg("Category Details", String.valueOf(categoryDetailsId));
        }
        if (!categoryDetails.isEmpty()) {
            log.info("Going to delete for {}", categoryDetails.get());
            AppUtility.constructSuccess("Successfully deleted", categoryDetails.get().getCategoryId());

            categoryDetailsRepository.delete(categoryDetails.get());
            log.info("Successfully deleted");
            return ResponseMessage.builder().responseMessage("Successfully deleted").build();
        }
        return null;
    }

}

