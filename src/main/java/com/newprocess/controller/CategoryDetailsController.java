package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CategoryDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.CategoryDetailsRepository;
import com.newprocess.service.CategoryDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/v1/api")
public class CategoryDetailsController {
    @Autowired
    private CategoryDetailsRepository categoryDetailsRepository;
    @Autowired
    private CategoryDetailsService categoryDetailsService;

    @GetMapping("/categoryDetails")
    public List<CategoryDetails> getAllCategoryDetails() {
        return categoryDetailsService.listAll();
    }

    @GetMapping("/categoryDetails/{id}")
    @Operation(summary = "Get Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))

    public ResponseMessage getCategoryDetailsById(@PathVariable(value = "id") Long categoryId)
            throws ResourceNotFoundException {
        return categoryDetailsService.getById(categoryId);
    }

    @PostMapping("/categoryDetails")
    @Operation(summary = "Processes Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))

    public ResponseMessage createCategoryDetails(@RequestBody CategoryDetails categoryDetails) {
        return categoryDetailsService.save(categoryDetails);
    }


    @DeleteMapping("/categoryDetails/{id}")
    public ResponseMessage deleteCategoryDetails(@PathVariable(value = "id") Long categoryDetailsId)
            throws ResourceNotFoundException {
        return categoryDetailsService.delete(categoryDetailsId);
    }
}