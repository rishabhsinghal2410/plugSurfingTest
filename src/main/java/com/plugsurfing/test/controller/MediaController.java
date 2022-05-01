package com.plugsurfing.test.controller;

import com.plugsurfing.test.domain.ArtistInformation;
import com.plugsurfing.test.service.MediaFacade;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MediaController.API_ROOT)
public class MediaController
{
    public static final String API_ROOT = "/musify/music-artist";

    private MediaFacade mediaFacade;

    @Autowired
    public MediaController(MediaFacade mediaFacade)
    {
        this.mediaFacade = mediaFacade;
    }


    @GetMapping("/details/{mbid}")
    public ArtistInformation getMusicArtistDetail(@PathVariable final UUID mbid){
        return this.mediaFacade.getDetails(mbid);
    }
}
