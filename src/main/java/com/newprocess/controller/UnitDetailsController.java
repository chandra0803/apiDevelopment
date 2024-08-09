package com.newprocess.controller;


import com.newprocess.ResourceNotFoundException;
import com.newprocess.api.ResponseMessage;
import com.newprocess.api.UnitDetails;
import com.newprocess.repositories.UnitDetailsRepository;
import com.newprocess.service.UnitDetailsService;
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
public class UnitDetailsController {
    @Autowired
    private UnitDetailsRepository unitDetailsRepository;
    @Autowired
    private UnitDetailsService unitDetailsService;

    @GetMapping("/unitDetails")
    public List<UnitDetails> getAllUnitDetails() {
        return unitDetailsService.listAll();
    }

    @GetMapping("/unitDetails/{id}")
    @Operation(summary = "Get UnitDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UnitDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = UnitDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = UnitDetails.class)))
    public ResponseMessage getUnitDetailsById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return unitDetailsService.getById(id);
    }

    @PostMapping("/unitDetails")
    @Operation(summary = "Processes UnitDetails Details")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UnitDetails.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request",
            content = @Content(schema = @Schema(implementation = UnitDetails.class)))
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = UnitDetails.class)))

    public ResponseMessage createUnitDetails(@RequestBody UnitDetails unitDetails) {
        return unitDetailsService.save(unitDetails);
    }


    @DeleteMapping("/unitDetails/{id}")
    public ResponseMessage deleteUnitDetails(@PathVariable(value = "id") Long unitDetailsId)
            throws ResourceNotFoundException {
        return unitDetailsService.delete(unitDetailsId);
    }
}