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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    public ResponseEntity<CategoryDetails> getCategoryDetailsById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        CategoryDetails categoryDetails = categoryDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Country Details not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(categoryDetails);
    }

    @PostMapping("/categoryDetails")
    @Operation(summary = "Processes Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CategoryDetails.class)))

    public ResponseEntity createCategoryDetails(@RequestBody CategoryDetails categoryDetails) {
        categoryDetailsService.save(categoryDetails);
        return ResponseEntity.ok("Country details successfully created");
    }


    @DeleteMapping("/categoryDetails/{id}")
    public ResponseMessage deleteCategoryDetails(@PathVariable(value = "id") Long categoryDetailsId)
            throws ResourceNotFoundException {
        return categoryDetailsService.delete(categoryDetailsId);
    }
}