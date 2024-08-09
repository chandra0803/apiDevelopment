package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.OperatorDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.OperatorDetailsRepository;
import com.newprocess.service.OperatorDetailsService;
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
public class OperatorDetailsController {
    @Autowired
    private OperatorDetailsRepository operatorDetailsRepository;
    @Autowired
    private OperatorDetailsService operatorDetailsService;

    @GetMapping("/operatorDetails")
    public List<OperatorDetails> getAllOperatorDetails() {
        return operatorDetailsService.listAll();
    }

    @GetMapping("/operatorDetails/{id}")
    @Operation(summary = "Get Operator Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))

    public ResponseMessage getOperatorDetailsById(@PathVariable(value = "id") Long operatorId)
            throws ResourceNotFoundException {
        return operatorDetailsService.getById(operatorId);
    }

    @PostMapping("/operatorDetails")
    @Operation(summary = "Processes Operator Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))

    public ResponseMessage createOperatorDetails(@RequestBody OperatorDetails operatorDetails) {
        return operatorDetailsService.save(operatorDetails);
    }

    @DeleteMapping("/operatorDetails/{id}")
    public ResponseMessage deleteOperatorDetails(@PathVariable(value = "id") Long operatorDetailsId)
            throws ResourceNotFoundException {
        return operatorDetailsService.delete(operatorDetailsId);
    }
}