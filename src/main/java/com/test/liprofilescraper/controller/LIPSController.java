package com.test.liprofilescraper.controller;

import com.test.liprofilescraper.exceptions.LIPSUniversalException;
import com.test.liprofilescraper.model.JobType;
import com.test.liprofilescraper.model.MyJobs;
import com.test.liprofilescraper.service.SavedJobsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "LI Profile Jobs Scraper", description = "Share your LinkedIn browser cookie, fetch your saved jobs data in a readable format.")
public class LIPSController {
    @Autowired
    private SavedJobsService savedJobsService;


    @Operation(summary = "Fetch SAVED / IN_PROGRESS / APPLIED / ARCHIVED jobs", description = "Select the job type from dropdown and provide your LinkedIn li_at Cookie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MyJobs.class)))),
    })
    @GetMapping(value = "/savedjobs", produces = {"application/json"})
    public List<MyJobs> getSavedJobs(@RequestParam JobType jobType,
                                     @Parameter(example = "li_at=xxxx;")
                                     @RequestParam String cookie) {
        try {
            return savedJobsService.getJobs(jobType, cookie);
        } catch (Exception e) {
            throw new LIPSUniversalException(e.getMessage());
        }
    }
}
