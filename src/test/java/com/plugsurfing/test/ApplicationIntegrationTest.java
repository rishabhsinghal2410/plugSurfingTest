package com.plugsurfing.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationIntegrationTest
{
    @Autowired
    private MockMvc mvc;


    @Test
    public void testServiceResponse_for_Michael_Jackson() throws Exception
    {
        mvc.perform(get("/musify/music-artist/details/f27ec8db-af05-4f36-916e-3d57f91ecf5e")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", is("Michael Jackson")))
            .andExpect(jsonPath("$.mbid", is("f27ec8db-af05-4f36-916e-3d57f91ecf5e")))
            .andExpect(jsonPath("$.gender", is("Male")))
            .andExpect(jsonPath("$.description", containsString("Michael Joseph Jackson was an American singer, songwriter, and dancer. Dubbed the \"King of Pop\", he is " +
                "regarded as one of the most significant cultural figures of the 20th century. Over a four-decade career, his contributions to music, dance, and fashion, along with his publicized personal life, made him a global figure in popular culture. Jackson influenced artists across many music genres; through stage and video performances, he popularized complicated dance moves such as the moonwalk, to which he gave the name, as well as the robot. He is the most awarded individual music artist in history.")))
            .andExpect(jsonPath("$.albums", notNullValue()));
    }


    @Test
    public void testServiceResponse_for_Janet_Jackson() throws Exception
    {
        mvc.perform(get("/musify/music-artist/details/6be2828f-6c0d-4059-99d4-fa18acf1a296")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", is("Janet Jackson")))
            .andExpect(jsonPath("$.mbid", is("6be2828f-6c0d-4059-99d4-fa18acf1a296")))
            .andExpect(jsonPath("$.gender", is("Female")))
            .andExpect(jsonPath("$.description", containsString("Janet Damita Jo Jackson is an American singer, songwriter, actress, and dancer. She is noted for her innovative, socially conscious and sexually provocative records, as well as elaborate stage shows. Her sound and choreography became a catalyst in the growth of MTV, enabling her to rise to prominence while breaking gender and racial barriers in the process. Lyrical content which focused on social issues set her reputation as a role model for youth.")))
            .andExpect(jsonPath("$.albums", notNullValue()));
    }
}
