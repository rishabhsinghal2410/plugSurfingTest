package com.plugsurfing.test.client;

import com.plugsurfing.test.domain.WikipediaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WikipediaClient
{

    private final RestTemplate restTemplate;


    @Autowired
    public WikipediaClient(@Qualifier("wikipedia") RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    public WikipediaResponse getDetails(final String resourceName)
    {
        return restTemplate.getForObject("/" + resourceName, WikipediaResponse.class);
    }

}
