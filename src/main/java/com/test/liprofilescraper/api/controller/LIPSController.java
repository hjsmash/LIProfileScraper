package com.test.liprofilescraper.api.controller;

import com.test.liprofilescraper.api.model.MyJobs;
import com.test.liprofilescraper.service.SavedJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LIPSController {
    @Autowired
    private SavedJobsService savedJobsService;

    @GetMapping("/savedjobs")
    public List<MyJobs> getSavedJobs(@RequestParam String jobType, @RequestParam String cookie) {
        return savedJobsService.getJobs(jobType, cookie);
    }
}
