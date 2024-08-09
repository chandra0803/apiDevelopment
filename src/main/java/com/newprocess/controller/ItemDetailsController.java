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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    public ResponseMessage getItemDetailsById(@PathVariable(value = "id") Long itemDetailsId)
            throws ResourceNotFoundException {
        return itemDetailsService.getById(itemDetailsId);
    }

    @PostMapping("/itemDetails")
    @Operation(summary = "Processes ItemDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = ItemDetails.class)))

    public ResponseMessage createItemDetails(@RequestBody ItemDetails itemDetails) {
        return itemDetailsService.save(itemDetails);
    }

    @DeleteMapping("/itemDetails/{id}")
    public ResponseMessage deleteItemDetails(@PathVariable(value = "id") Long itemDetailsId)
            throws ResourceNotFoundException {
        return itemDetailsService.delete(itemDetailsId);
    }
}