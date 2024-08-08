package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.StateDetails;
import com.newprocess.repositories.StateDetailsRepository;
import com.newprocess.service.StateDetailsService;
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
public class StateDetailsController {
    @Autowired
    private StateDetailsRepository stateDetailsRepository;
    @Autowired
    private StateDetailsService stateDetailsService;

    @GetMapping("/stateDetails")
    public List<StateDetails> getAllStateDetails() {
        return stateDetailsService.listAll();
    }

    @GetMapping("/stateDetails/{id}")
    @Operation(summary = "Get StateDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))

    public ResponseEntity<StateDetails> getStateDetailsById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        StateDetails stateDetails = stateDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("StateDetails Details not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(stateDetails);
    }

    @PostMapping("/stateDetails")
    @Operation(summary = "Processes StateDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))

    public ResponseEntity createStateDetails(@RequestBody StateDetails stateDetails) {
        stateDetailsService.save(stateDetails);
        return ResponseEntity.ok("StateDetails details successfully created");
    }

    @DeleteMapping("/stateDetails/{id}")
    public ResponseMessage deleteStateDetails(@PathVariable(value = "id") Long stateDetailsId)
            throws ResourceNotFoundException {
       return stateDetailsService.delete(stateDetailsId);
    }
}