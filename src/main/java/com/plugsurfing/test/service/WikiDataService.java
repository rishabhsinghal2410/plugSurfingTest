package com.plugsurfing.test.service;

import com.plugsurfing.test.client.WikiDataClient;
import com.plugsurfing.test.domain.MusicBrainzResponse;
import com.plugsurfing.test.domain.WikiDataResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikiDataService
{
    private WikiDataClient client;


    @Autowired
    public WikiDataService(WikiDataClient client)
    {
        this.client = client;
    }


    public String getTitle(final MusicBrainzResponse musicBrainzResponse)
    {
        return musicBrainzResponse.getRelations().parallelStream().map(relation -> {
            String[] strings = relation.getUrl().getOrDefault("resource", "").split("/");
            String resourceId = strings[strings.length - 1];
            WikiDataResponse response = client.getResponse(resourceId);
            return response.getEntities().get(resourceId).getSitelinks().get("enwiki").getTitle();
        }).findFirst().orElse("");
    }
}
