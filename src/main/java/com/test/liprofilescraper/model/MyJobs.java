package com.test.liprofilescraper.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyJobs {
    @JsonProperty("Role")
    private String role;
    @JsonProperty("Company")
    private String company;
    @JsonProperty("Location")
    private String location;
    @JsonProperty("Posted")
    private String posted;
    @JsonProperty("Applied")
    private String applied;

    public MyJobs(String role, String company, String location, String postedOrApplied) {
        this.role = role;
        this.company = company;
        this.location = location;
        if (postedOrApplied.startsWith("Applied")) {
            this.applied = postedOrApplied.substring("Applied".length()).trim();
        } else if (postedOrApplied.startsWith("Posted")) {
            this.posted = postedOrApplied.substring("Posted".length()).trim();
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getApplied() {
        return applied;
    }

    public void setApplied(String applied) {
        this.applied = applied;
    }
}
