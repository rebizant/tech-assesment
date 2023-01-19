package com.rebizant.techassesment;

import com.rebizant.techassesment.graph.CountryGraph;
import com.rebizant.techassesment.graph.parser.CountryDataProvider;
import com.rebizant.techassesment.graph.parser.JsonTextResourceCountryProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class GraphConfiguration {

    @Bean
    public CountryDataProvider countryDataProvider(@Value("classpath:countries.json") Resource data) {
        return new JsonTextResourceCountryProvider(data);
    }

    @Bean
    public CountryGraph countryGraph(CountryDataProvider countryDataProvider) throws IOException {

        CountryGraph graph = new CountryGraph();
        countryDataProvider.getCountries()
                .forEach(country -> graph.addCountry(country.getCode(), country.getNeighbours()));
        return graph;
    }
}
