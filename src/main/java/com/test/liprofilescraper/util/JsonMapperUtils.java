package com.test.liprofilescraper.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.liprofilescraper.api.model.MyJobs;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonMapperUtils {
    public static List<MyJobs> mapLISavedJobApiResponseToMyJobsList(ResponseEntity<String> response) {
        JsonObject root = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        JsonArray jsonArray = root.getAsJsonObject("data").
                getAsJsonObject("searchDashClustersByAll").getAsJsonArray("elements").get(0).getAsJsonObject().getAsJsonArray("items");

        List<MyJobs> itemList = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            ;
            JsonObject entityResult = jsonObject.getAsJsonObject("item").getAsJsonObject("entityResult");
            JsonArray insightsResolutionResults = entityResult.getAsJsonArray("insightsResolutionResults");
            MyJobs item = new MyJobs(
                    entityResult.getAsJsonObject("title").get("text").getAsString(),
                    entityResult.getAsJsonObject("primarySubtitle").get("text").getAsString(),
                    entityResult.getAsJsonObject("secondarySubtitle").get("text").getAsString(),
                    insightsResolutionResults.get(insightsResolutionResults.size() - 1).getAsJsonObject().getAsJsonObject("simpleInsight").getAsJsonObject("title").get("text").getAsString()
            );
            itemList.add(item);
        }
        return itemList;
    }
}
