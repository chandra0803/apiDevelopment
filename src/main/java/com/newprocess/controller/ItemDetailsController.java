package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ItemDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.ItemDetailsRepository;
import com.newprocess.service.ItemDetailsService;
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
public class ItemDetailsController {
    @Autowired
    private ItemDetailsRepository itemDetailsRepository;
    @Autowired
    private ItemDetailsService itemDetailsService;

    @GetMapping("/itemDetails")
    public List<ItemDetails> getAllItemDetails() {
        return itemDetailsService.listAll();
    }

    @GetMapping("/itemDetails/{id}")
    @Operation(summary = "Get ItemDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))

    public ResponseEntity<ItemDetails> getItemDetailsById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        ItemDetails itemDetails = itemDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("ItemDetails Details not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(itemDetails);
    }

    @PostMapping("/itemDetails")
    @Operation(summary = "Processes ItemDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))

    public ResponseEntity createItemDetails(@RequestBody ItemDetails itemDetails) {
        itemDetailsService.save(itemDetails);
        return ResponseEntity.ok("ItemDetails details successfully created");
    }

    @DeleteMapping("/itemDetails/{id}")
    public ResponseMessage deleteItemDetails(@PathVariable(value = "id") Long itemDetailsId)
            throws ResourceNotFoundException {
      return itemDetailsService.delete(itemDetailsId);
    }
}