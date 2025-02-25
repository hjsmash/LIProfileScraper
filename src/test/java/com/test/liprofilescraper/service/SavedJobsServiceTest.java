package com.test.liprofilescraper.service;

import com.test.liprofilescraper.model.JobType;
import com.test.liprofilescraper.model.MyJobs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SavedJobsServiceTest {

    @Mock
    private LIScraperService liScraperService;

    @InjectMocks
    private SavedJobsService savedJobsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetJobs() {
        JobType jobType = JobType.ARCHIVED;
        String cookie = "testCookie";
        List<MyJobs> expectedJobs = new ArrayList<>();
        expectedJobs.add(new MyJobs("Job Title 1", "Company 1", "Location 1", null));

        when(liScraperService.getLISavedJobsData(jobType, cookie)).thenReturn(expectedJobs);

        List<MyJobs> actualJobs = savedJobsService.getJobs(jobType, cookie);

        assertNotNull(actualJobs);
        assertEquals(expectedJobs.size(), actualJobs.size());
        assertEquals(expectedJobs.get(0).getRole(), actualJobs.get(0).getRole());
        assertEquals(expectedJobs.get(0).getCompany(), actualJobs.get(0).getCompany());
        assertEquals(expectedJobs.get(0).getLocation(), actualJobs.get(0).getLocation());
        assertNull(actualJobs.get(0).getApplied());
        assertNull(actualJobs.get(0).getPosted());

        verify(liScraperService, times(1)).getLISavedJobsData(jobType, cookie);
    }
}
