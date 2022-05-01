package com.plugsurfing.test.domain;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WikiDataResponse
{
    private Map<String, ResourceData> entities;


    @Getter
    @Setter
    public static class ResourceData
    {
        private Map<String, SiteLink> sitelinks;
    }

    @Getter
    @Setter
    public static class SiteLink
    {
        private String title;
    }
}
