package com.plugsurfing.test.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoverArtResponse
{
    private List<Image> images;

    @Getter
    @Setter
    public static class Image{
        private String image;
    }
}
