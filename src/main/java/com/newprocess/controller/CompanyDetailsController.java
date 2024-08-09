package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.CompanyDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.CompanyDetailsRepository;
import com.newprocess.service.CompanyDetailsService;
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
public class CompanyDetailsController {
    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;
    @Autowired
    private CompanyDetailsService companyDetailsService;

    @GetMapping("/companyDetails")
    public List<CompanyDetails> getAllCompanyDetails() {
        return companyDetailsService.listAll();
    }

    @GetMapping("/companyDetails/{id}")
    @Operation(summary = "Get Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CompanyDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CompanyDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CompanyDetails.class)))

    public ResponseMessage getCompanyDetailsById(@PathVariable(value = "id") Long companyId)
            throws ResourceNotFoundException {
        return companyDetailsService.getById(companyId);
    }

    @PostMapping("/companyDetails")
    @Operation(summary = "Processes Company Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = CompanyDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = CompanyDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = CompanyDetails.class)))

    public ResponseMessage createCompanyDetails(@RequestBody CompanyDetails companyDetails) {
        return companyDetailsService.save(companyDetails);
    }


    @DeleteMapping("/companyDetails/{id}")
    public ResponseMessage deleteCompanyDetails(@PathVariable(value = "id") Long companyDetailsId)
            throws ResourceNotFoundException {
        return companyDetailsService.delete(companyDetailsId);
    }
}