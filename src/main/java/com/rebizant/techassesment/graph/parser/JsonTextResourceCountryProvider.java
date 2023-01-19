package com.rebizant.techassesment.graph.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JsonTextResourceCountryProvider implements CountryDataProvider {

    private final Resource resource;

    public JsonTextResourceCountryProvider(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Collection<CountryDefinition> getCountries() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return mapper.readValue(reader, new TypeReference<List<CountryDefinition>>() {
            });
        }

    }

}
