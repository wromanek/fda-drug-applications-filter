package com.romanek.drugs.fda.rest;

import com.romanek.drugs.fda.FdaDrugRecordApplicationService;
import com.romanek.drugs.fda.model.DrugRecordApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drugs/fda")
@RequiredArgsConstructor
class FdaDrugRecordApplicationController {

    private final FdaDrugRecordApplicationService fdaDrugRecordApplicationService;

    @GetMapping
    DrugRecordApplication filterDrugs(@RequestParam String manufacturerName, @RequestParam(required = false) String brandName,
                                      @RequestParam(required = false, defaultValue = "0") int skip,
                                      @RequestParam(required = false, defaultValue = "1") int limit) {
        return fdaDrugRecordApplicationService.filterApplications(manufacturerName, brandName, skip, limit);
    }
}
