package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.BranchDetails;
import com.newprocess.api.ResponseMessage;
import com.newprocess.repositories.BranchDetailsRepository;
import com.newprocess.service.BranchDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/v1/api")
public class BranchDetailsController {
    @Autowired
    private BranchDetailsRepository branchDetailsRepository;
    @Autowired
    private BranchDetailsService branchDetailsService;

    @GetMapping("/branchDetails")
    public List<BranchDetails> getAllBranchDetails() {
        return branchDetailsService.listAll();
    }

    @GetMapping("/branchDetails/{id}")
    @Operation(summary = "Get Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = BranchDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = BranchDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = BranchDetails.class)))

    public ResponseEntity<BranchDetails> getBranchDetailsById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        BranchDetails branchDetails = branchDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch Details not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(branchDetails);
    }

    @PostMapping("/branchDetails")
    @Operation(summary = "Processes Branch Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = BranchDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = BranchDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = BranchDetails.class)))

    public ResponseEntity createBranchDetails(@RequestBody BranchDetails branchDetails) {
        branchDetailsService.save(branchDetails);
        return ResponseEntity.ok("Branch details successfully created");
    }

    @DeleteMapping("/branchDetails/{id}")
    public ResponseMessage deleteBranchDetails(@PathVariable(value = "id") Long branchDetailsId)
            throws ResourceNotFoundException {
        return branchDetailsService.delete(branchDetailsId);
    }
}