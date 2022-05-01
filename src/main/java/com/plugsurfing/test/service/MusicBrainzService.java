package com.plugsurfing.test.service;

import com.plugsurfing.test.client.MusicBrainzClient;
import com.plugsurfing.test.domain.MusicBrainzResponse;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicBrainzService
{
    private MusicBrainzClient client;


    @Autowired
    public MusicBrainzService(MusicBrainzClient client)
    {
        this.client = client;
    }


    public MusicBrainzResponse getDetails(final UUID mbid)
    {
        MusicBrainzResponse musicBrainzResponse = client.getResponse(mbid);

        musicBrainzResponse.setRelations(
            musicBrainzResponse.getRelations()
                .parallelStream()
                .filter(relation -> "wikidata".equalsIgnoreCase(relation.getType()))
                .collect(Collectors.toList()));

        return musicBrainzResponse;
    }
}
