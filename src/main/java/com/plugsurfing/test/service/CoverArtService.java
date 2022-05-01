package com.plugsurfing.test.service;

import com.plugsurfing.test.client.CoverArtClient;
import com.plugsurfing.test.domain.CoverArtResponse;
import com.plugsurfing.test.domain.MusicBrainzResponse;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverArtService
{
    private static final Logger LOG = LoggerFactory.getLogger(CoverArtService.class);
    private CoverArtClient client;


    @Autowired
    public CoverArtService(CoverArtClient client)
    {
        this.client = client;
    }


    public List<CoverArtResponse> getImage(final List<MusicBrainzResponse.ReleaseGroup> releaseGroups)
    {
        List<Future<CoverArtResponse>> futures = releaseGroups.parallelStream().map(releaseGroup -> client.getDetails(releaseGroup.getId())).collect(Collectors.toList());
        return futures.parallelStream().map(a -> {
            try
            {
                return a.get();
            }
            catch (Exception exception)
            {
                LOG.error("Unable to get the cover art, adding default place holder");
                return new CoverArtResponse();
            }
        }).collect(Collectors.toList());
    }
}
