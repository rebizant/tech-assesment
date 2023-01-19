package com.rebizant.techassesment.service;

import com.rebizant.techassesment.graph.CountryGraph;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final CountryGraph countryGraph;

    public RouteService(CountryGraph countryGraph) {
        this.countryGraph = countryGraph;
    }

    public Optional<List<String>> calculateRoute(String origin, String destination) {
        if (!(countryGraph.hasCountry(origin) && countryGraph.hasCountry(destination))) {
            return Optional.empty();
        }

        List<String> path = countryGraph.getRoute(origin, destination);
        return path.isEmpty() ? Optional.empty() : Optional.of(path);
    }
}
