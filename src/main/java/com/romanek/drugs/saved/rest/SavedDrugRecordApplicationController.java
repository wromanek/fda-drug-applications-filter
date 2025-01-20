package com.romanek.drugs.saved.rest;

import com.romanek.drugs.saved.SavedDrugRecordApplicationService;
import com.romanek.drugs.saved.model.DrugRecordApplicationQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/saved-applications")
@RequiredArgsConstructor
class SavedDrugRecordApplicationController {

    private final SavedDrugRecordApplicationService savedDrugRecordApplicationService;

    @PostMapping
    @Operation(summary = "Save application by query. Lets the user save a drug record application found in openFDA using query object.")
    @ApiResponse(responseCode = "201", description = "Successfully persisted drug record application.")
    @ResponseStatus(HttpStatus.CREATED)
    void saveApplicationByQuery(@RequestBody DrugRecordApplicationQuery drugRecordApplicationQuery) {
        savedDrugRecordApplicationService.saveApplicationByQuery(drugRecordApplicationQuery);
    }

    @GetMapping
    @Operation(summary = "Get saved applications. Lets the user get a list of saved drug record applications.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved saved drug record applications.")
    Page<Map<String, Object>> getCustomDrugs(@RequestParam(defaultValue = "0")
                                             @Parameter(description = "Offset for pagination", example = "0")
                                             int offset,
                                             @RequestParam(defaultValue = "10")
                                             @Parameter(description = "Size for pagination", example = "10")
                                             int size) {
        return savedDrugRecordApplicationService.getSavedDrugRecordApplications(PageRequest.of(offset, size));
    }
}
