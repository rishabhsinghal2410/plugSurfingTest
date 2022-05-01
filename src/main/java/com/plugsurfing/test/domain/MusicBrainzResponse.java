package com.plugsurfing.test.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicBrainzResponse
{
    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups;

    private List<Relation> relations;

    private String gender;
    private String disambiguation;
    private String name;
    private String country;

    @Getter
    @Setter
    public static class ReleaseGroup
    {
        private UUID id;
        private String title;
    }

    @Getter
    @Setter
    public static class Relation
    {
        private String type;

        private Map<String, String> url;
    }
}
