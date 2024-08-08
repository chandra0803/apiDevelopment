package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.RateDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.RateDetailsRepository;
import com.newprocess.service.RateDetailsService;
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
public class RateDetailsController {
    @Autowired
    private RateDetailsRepository rateDetailsRepository;
    @Autowired
    private RateDetailsService rateDetailsService;

    @GetMapping("/rateDetails")
    public List<RateDetails> getAllRateDetails() {
        return rateDetailsService.listAll();
    }

    @GetMapping("/rateDetails/{id}")
    @Operation(summary = "Get RateDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = RateDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RateDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = RateDetails.class)))

    public ResponseEntity<RateDetails> getRateDetailsById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        RateDetails rateDetails = rateDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("RateDetails Details not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(rateDetails);
    }

    @PostMapping("/rateDetails")
    @Operation(summary = "Processes RateDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = RateDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = RateDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = RateDetails.class)))

    public ResponseEntity createRateDetails(@RequestBody RateDetails rateDetails) {
        rateDetailsService.save(rateDetails);
        return ResponseEntity.ok("RateDetails details successfully created");
    }

    @DeleteMapping("/rateDetails/{id}")
    public ResponseMessage deleteRateDetails(@PathVariable(value = "id") Long rateDetailsId)
            throws ResourceNotFoundException {
       return rateDetailsService.delete(rateDetailsId);
    }
}