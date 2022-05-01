package com.plugsurfing.test.client;

import com.plugsurfing.test.domain.WikiDataResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WikiDataClient
{

    private final RestTemplate restTemplate;


    @Autowired
    public WikiDataClient(@Qualifier("wikiData") RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    public WikiDataResponse getResponse(final String resourceId)
    {
        return restTemplate.getForObject("/" + resourceId + ".json", WikiDataResponse.class);
    }

}
