package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ItemBatchDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.ItemBatchDetailsRepository;
import com.newprocess.service.ItemBatchDetailsService;
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
public class ItemBatchDetailsController {
    @Autowired
    private ItemBatchDetailsRepository itemBatchDetailsRepository;
    @Autowired
    private ItemBatchDetailsService itemBatchDetailsService;

    @GetMapping("/itemBatchDetails")
    public List<ItemBatchDetails> getAllItemBatchDetails() {
        return itemBatchDetailsService.listAll();
    }

    @GetMapping("/itemBatchDetails/{id}")
    @Operation(summary = "Get ItemBatch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ItemBatchDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ItemBatchDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ItemBatchDetails.class)))

    public ResponseMessage getItemBatchDetailsById(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException {
        return itemBatchDetailsService.getById(itemId);
    }

    @PostMapping("/itemBatchDetails")
    @Operation(summary = "Processes ItemBatch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ItemBatchDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ItemBatchDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ItemBatchDetails.class)))

    public ResponseMessage createItemBatchDetails(@RequestBody ItemBatchDetails itemBatchDetails) {
        return itemBatchDetailsService.save(itemBatchDetails);
    }

    @DeleteMapping("/itemBatchDetails/{id}")
    public ResponseMessage deleteItemBatchDetails(@PathVariable(value = "id") Long itemBatchDetailsId)
            throws ResourceNotFoundException {
        return itemBatchDetailsService.delete(itemBatchDetailsId);
    }
}