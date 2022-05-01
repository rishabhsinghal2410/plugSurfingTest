package com.plugsurfing.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig
{

    @Bean(name = "jacksonConverter")
    public MappingJackson2HttpMessageConverter converters(final ObjectMapper objectMapper) {

        final MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();

        httpMessageConverter.setObjectMapper(objectMapper);
        //httpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        return httpMessageConverter;
    }

    @Bean(name = "musicBrainz")
    public RestTemplate musicBrainzRestTemplate(@Qualifier("jacksonConverter") final MappingJackson2HttpMessageConverter jacksonConverter, final ResponseErrorHandler responseErrorHandler){
        return new RestTemplateBuilder().messageConverters(Collections.singletonList(jacksonConverter)).errorHandler(responseErrorHandler).rootUri("https://musicbrainz.org/ws/2/artist/").build();
    }

    @Bean(name = "wikiData")
    public RestTemplate wikiDataRestTemplate(@Qualifier("jacksonConverter") final MappingJackson2HttpMessageConverter jacksonConverter, final ResponseErrorHandler responseErrorHandler){
        return new RestTemplateBuilder().messageConverters(Collections.singletonList(jacksonConverter)).errorHandler(responseErrorHandler).rootUri("https://www.wikidata" +
            ".org/wiki/Special:EntityData/").build();
    }

    @Bean(name = "wikipedia")
    public RestTemplate wikipediaRestTemplate(@Qualifier("jacksonConverter") final MappingJackson2HttpMessageConverter jacksonConverter, final ResponseErrorHandler responseErrorHandler){
        return new RestTemplateBuilder().messageConverters(Collections.singletonList(jacksonConverter)).errorHandler(responseErrorHandler).rootUri("https://en.wikipedia" +
            ".org/api/rest_v1/page/summary/").build();
    }

    @Bean(name = "coverArt")
    public RestTemplate coverArtRestTemplate(@Qualifier("jacksonConverter") final MappingJackson2HttpMessageConverter jacksonConverter, final ResponseErrorHandler responseErrorHandler){
        return new RestTemplateBuilder().messageConverters(Collections.singletonList(jacksonConverter)).errorHandler(responseErrorHandler).rootUri("http://coverartarchive.org/release-group/")
            .build();
    }
}
