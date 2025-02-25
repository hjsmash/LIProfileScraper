package com.test.liprofilescraper.service;

import com.test.liprofilescraper.model.JobType;
import com.test.liprofilescraper.model.MyJobs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.test.liprofilescraper.util.IConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LIScraperServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LIScraperService liScraperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLISavedJobsData() {
        // Arrange
        JobType jobType = JobType.APPLIED;
        String cookie = "testCookie";
        String mockResponse = "{\"data\": {\"searchDashClustersByAll\": {\"elements\": [{\"items\": [" +
                "{\"item\": {\"entityResult\": {\"title\": {\"text\": \"Job Title 1\"}, " +
                "\"primarySubtitle\": {\"text\": \"Company 1\"}, " +
                "\"secondarySubtitle\": {\"text\": \"Location 1\"}, " +
                "\"insightsResolutionResults\": [{\"simpleInsight\": {\"title\": {\"text\": \"Applied Insight 1\"}}}]}}}]}, {\"items\": []}]}}}";

        ResponseEntity<String> responseEntity = ResponseEntity.ok(mockResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie + JSESSIONID);
        headers.set("Csrf-Token", CSRFTOKEN);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        when(restTemplate.exchange(
                eq(SAVEDJOBURI.replace("JOB_TYPE", jobType.name())),
                eq(HttpMethod.GET),
                eq(requestEntity),
                eq(String.class)
        )).thenReturn(responseEntity);

        List<MyJobs> expectedJobs = new ArrayList<>();
        expectedJobs.add(new MyJobs("Job Title 1", "Company 1", "Location 1", "Applied Insight 1"));

        List<MyJobs> actualJobs = liScraperService.getLISavedJobsData(jobType, cookie);

        assertNotNull(actualJobs);
        assertEquals(expectedJobs.size(), actualJobs.size());
        assertEquals(expectedJobs.get(0).getRole(), actualJobs.get(0).getRole());
        assertEquals(expectedJobs.get(0).getCompany(), actualJobs.get(0).getCompany());
        assertEquals(expectedJobs.get(0).getLocation(), actualJobs.get(0).getLocation());
        assertEquals(expectedJobs.get(0).getApplied(), actualJobs.get(0).getApplied());

        verify(restTemplate, times(1)).exchange(
                eq(SAVEDJOBURI.replace("JOB_TYPE", jobType.name())),
                eq(HttpMethod.GET),
                eq(requestEntity),
                eq(String.class)
        );
    }
}
