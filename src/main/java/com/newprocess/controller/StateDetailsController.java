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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    public ResponseMessage getStateDetailsById(@PathVariable(value = "id") Long stateId)
            throws ResourceNotFoundException {
        return stateDetailsService.getById(stateId);
    }

    @PostMapping("/stateDetails")
    @Operation(summary = "Processes StateDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = StateDetails.class)))

    public ResponseMessage createStateDetails(@RequestBody StateDetails stateDetails) {
        return stateDetailsService.save(stateDetails);
    }

    @DeleteMapping("/stateDetails/{id}")
    public ResponseMessage deleteStateDetails(@PathVariable(value = "id") Long stateDetailsId)
            throws ResourceNotFoundException {
        return stateDetailsService.delete(stateDetailsId);
    }
}