package com.test.liprofilescraper.util;

import com.test.liprofilescraper.model.MyJobs;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonMapperUtilsTest {

    @Test
    void testMapLISavedJobApiResponseToMyJobsList() {
        String mockResponse = "{\"data\": {\"searchDashClustersByAll\": {\"elements\": [{\"items\": [" +
                "{\"item\": {\"entityResult\": {\"title\": {\"text\": \"Job Title 1\"}, " +
                "\"primarySubtitle\": {\"text\": \"Company 1\"}, " +
                "\"secondarySubtitle\": {\"text\": \"Location 1\"}, " +
                "\"insightsResolutionResults\": [{\"simpleInsight\": {\"title\": {\"text\": \"Applied Insight 1\"}}}]}}}]}, {\"items\": []}]}}}";


        ResponseEntity<String> responseEntity = ResponseEntity.ok(mockResponse);

        List<MyJobs> result = JsonMapperUtils.mapLISavedJobApiResponseToMyJobsList(responseEntity);

        assertNotNull(result);
        assertEquals(1, result.size());

        MyJobs job = result.get(0);
        assertEquals("Job Title 1", job.getRole());
        assertEquals("Company 1", job.getCompany());
        assertEquals("Location 1", job.getLocation());
        assertEquals("Insight 1", job.getApplied());
    }
}
