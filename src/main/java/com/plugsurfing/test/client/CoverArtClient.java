package com.plugsurfing.test.client;

import com.plugsurfing.test.domain.CoverArtResponse;
import java.util.UUID;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoverArtClient
{

    private final RestTemplate restTemplate;


    @Autowired
    public CoverArtClient(@Qualifier("coverArt") RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    @Async
    public Future<CoverArtResponse> getDetails(final UUID resourceId)
    {
        return new AsyncResult<CoverArtResponse>(restTemplate.getForObject("/" + resourceId, CoverArtResponse.class));
    }

}
