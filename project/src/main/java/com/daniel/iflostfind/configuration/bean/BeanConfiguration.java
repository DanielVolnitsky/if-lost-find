package com.daniel.iflostfind.configuration.bean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;

@Configuration
public class BeanConfiguration {

    @Value("${hidden.file.path}")
    private String googleMapsApiKeyHolder;

    @Bean
    public String googleMapKey() {
        return (String) hiddenInfoJSONObject().get("google-map-key");
    }

    @Bean
    public JSONObject hiddenInfoJSONObject() {
        try {
            return (JSONObject) jsonParser().parse(new FileReader(googleMapsApiKeyHolder));
        } catch (IOException | ParseException e) {
            //TODO log
            throw new RuntimeException("failed to parse hidden values json", e);
        }
    }

    @Bean
    public JSONParser jsonParser() {
        return new JSONParser();
    }
}
