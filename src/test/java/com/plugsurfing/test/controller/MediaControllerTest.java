package com.plugsurfing.test.controller;

import com.plugsurfing.test.domain.ArtistInformation;
import com.plugsurfing.test.service.MediaFacade;
import java.util.Collections;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MediaController.class)
public class MediaControllerTest
{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MediaFacade service;

    @Test
    public void testArtistInformationObject() throws Exception
    {
        ArtistInformation artistInformation = new ArtistInformation();
        artistInformation.setMbid(UUID.fromString("f27ec8db-af05-4f36-916e-3d57f91ecf5e"));
        artistInformation.setName("Michael Jackson");
        artistInformation.setCountry("US");
        artistInformation.setGender("Male");
        artistInformation.setDescription("Michael Joseph Jackson was an American singer, songwriter, and dancer.");
        artistInformation.setAlbums(Collections.singletonList(new ArtistInformation.Album(UUID.randomUUID(), "Test Album")));

        Mockito.when(service.getDetails(UUID.fromString("f27ec8db-af05-4f36-916e-3d57f91ecf5e"))).thenReturn(artistInformation);

        mvc.perform(get("/musify/music-artist/details/f27ec8db-af05-4f36-916e-3d57f91ecf5e")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", is("Michael Jackson")))
            .andExpect(jsonPath("$.mbid", is("f27ec8db-af05-4f36-916e-3d57f91ecf5e")))
            .andExpect(jsonPath("$.gender", is("Male")))
            .andExpect(jsonPath("$.description", is("Michael Joseph Jackson was an American singer, songwriter, and dancer.")))
            .andExpect(jsonPath("$.albums", notNullValue()));
    }
}
