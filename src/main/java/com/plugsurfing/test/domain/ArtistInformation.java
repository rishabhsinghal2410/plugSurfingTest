package com.plugsurfing.test.domain;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistInformation
{
    private UUID mbid;
    private String name;
    private String gender;
    private String country;
    private String description;
    private List<Album> albums;

    @Getter
    @Setter
    public static class Album{
        private UUID id;
        private String title;
        private String imageUrl;


        public Album(UUID id, String title)
        {
            this.id = id;
            this.title = title;
        }
    }
}
