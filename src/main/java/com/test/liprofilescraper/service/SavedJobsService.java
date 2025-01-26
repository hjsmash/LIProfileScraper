package com.test.liprofilescraper.service;

import com.test.liprofilescraper.api.model.MyJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobsService {
    @Autowired
    private LIScraperService liScraperService;

    public List<MyJobs> getJobs(String jobType, String cookie) {
        return liScraperService.getLISavedJobsData(jobType, cookie);
    }
}
