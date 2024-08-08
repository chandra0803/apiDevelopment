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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    public ResponseEntity<OperatorDetails> getOperatorDetailsById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        OperatorDetails operatorDetails = operatorDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Operator Details not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(operatorDetails);
    }

    @PostMapping("/operatorDetails")
    @Operation(summary = "Processes Operator Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = OperatorDetails.class)))

    public ResponseEntity createOperatorDetails(@RequestBody OperatorDetails operatorDetails) {
        operatorDetailsService.save(operatorDetails);
        return ResponseEntity.ok("Operator details successfully created");
    }

    @DeleteMapping("/operatorDetails/{id}")
    public ResponseMessage deleteOperatorDetails(@PathVariable(value = "id") Long operatorDetailsId)
            throws ResourceNotFoundException {
       return operatorDetailsService.delete(operatorDetailsId);
    }
}