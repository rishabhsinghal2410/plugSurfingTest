package com.plugsurfing.test.client;

import com.plugsurfing.test.domain.MusicBrainzResponse;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicBrainzClient
{

    private final RestTemplate restTemplate;
    private String url = "/%s?&fmt=json&inc=url-rels+release-groups";


    @Autowired
    public MusicBrainzClient(@Qualifier("musicBrainz") RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    public MusicBrainzResponse getResponse(final UUID mbid)
    {
        return restTemplate.getForObject(String.format(url, mbid), MusicBrainzResponse.class);
    }

}
