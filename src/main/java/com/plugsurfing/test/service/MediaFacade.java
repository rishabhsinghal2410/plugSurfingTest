package com.plugsurfing.test.service;

import com.plugsurfing.test.client.WikipediaClient;
import com.plugsurfing.test.domain.ArtistInformation;
import com.plugsurfing.test.domain.CoverArtResponse;
import com.plugsurfing.test.domain.MusicBrainzResponse;
import com.plugsurfing.test.domain.WikipediaResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaFacade
{

    private final MusicBrainzService musicBrainzService;
    private final WikiDataService wikiDataService;
    private final WikipediaClient wikipediaClient;
    private final CoverArtService coverArtService;


    @Autowired
    public MediaFacade(
        final MusicBrainzService musicBrainzService,
        WikiDataService wikiDataService,
        WikipediaClient wikipediaClient,
        CoverArtService coverArtService)
    {
        this.musicBrainzService = musicBrainzService;
        this.wikiDataService = wikiDataService;
        this.wikipediaClient = wikipediaClient;
        this.coverArtService = coverArtService;
    }


    public ArtistInformation getDetails(final UUID mbid)
    {
        MusicBrainzResponse musicBrainzResponse = musicBrainzService.getDetails(mbid);
        String title = wikiDataService.getTitle(musicBrainzResponse);
        WikipediaResponse details = wikipediaClient.getDetails(title);
        List<CoverArtResponse> images = coverArtService.getImage(musicBrainzResponse.getReleaseGroups());

        ArtistInformation information = new ArtistInformation();
        information.setMbid(mbid);
        information.setName(musicBrainzResponse.getName());
        information.setGender(musicBrainzResponse.getGender());
        information.setCountry(musicBrainzResponse.getCountry());
        information.setDescription(details.getExtract());
        information.setAlbums( musicBrainzResponse.getReleaseGroups().parallelStream().map(releaseGroup -> new ArtistInformation.Album(releaseGroup.getId(), releaseGroup.getTitle())).collect(Collectors.toList()));
        return information;
    }
}
