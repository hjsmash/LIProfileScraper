package com.test.liprofilescraper.service;

import com.test.liprofilescraper.api.model.MyJobs;
import com.test.liprofilescraper.util.JsonMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.test.liprofilescraper.IConstants.*;

@Service
public class LIScraperService {


    @Autowired
    private RestTemplate restTemplate;

    public List<MyJobs> getLISavedJobsData(String jobType, String cookie) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Cookie", cookie + JSESSIONID);
            headers.set("Csrf-Token", CSRFTOKEN);


            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    SAVEDJOBURI.replace("JOB_TYPE", jobType), HttpMethod.GET, requestEntity, String.class);


            List<MyJobs> myJobsList = new ArrayList<>();
            myJobsList.addAll(JsonMapperUtils.mapLISavedJobApiResponseToMyJobsList(response));

            return myJobsList;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Handle client and server errors
            return null;
        }
    }


}
