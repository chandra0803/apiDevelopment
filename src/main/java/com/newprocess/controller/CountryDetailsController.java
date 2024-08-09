package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CountryDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.CountryDetailsRepository;
import com.newprocess.service.CountryDetailsService;
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
public class CountryDetailsController {
    @Autowired
    private CountryDetailsRepository countryDetailsRepository;
    @Autowired
    private CountryDetailsService countryDetailsService;

    @GetMapping("/countryDetails")
    public List<CountryDetails> getAllCountryDetails() {
        return countryDetailsService.listAll();
    }

    @GetMapping("/countryDetails/{id}")
    @Operation(summary = "Get Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CountryDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CountryDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CountryDetails.class)))

    public ResponseMessage getCountryDetailsById(@PathVariable(value = "id") Long countryId)
            throws ResourceNotFoundException {
        return countryDetailsService.getById(countryId);
    }

    @PostMapping("/countryDetails")
    @Operation(summary = "Processes Country Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CountryDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CountryDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CountryDetails.class)))

    public ResponseMessage createCountryDetails(@RequestBody CountryDetails countryDetails) {
        return countryDetailsService.save(countryDetails);
    }

    @DeleteMapping("/countryDetails/{id}")
    public ResponseMessage deleteCountryDetails(@PathVariable(value = "id") Long countryDetailsId)
            throws ResourceNotFoundException {
        return countryDetailsService.delete(countryDetailsId);
    }
}