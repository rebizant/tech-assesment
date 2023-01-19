package com.rebizant.techassesment.service;

import com.rebizant.techassesment.graph.CountryGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RouteServiceTest {

    private RouteService routeService;
    private CountryGraph countryGraph;

    @BeforeEach
    void init() {
        countryGraph = Mockito.mock(CountryGraph.class);
        routeService = new RouteService(countryGraph);
    }

    @Test
    void missingCountryCodesProvidedTest() {
        //given
        Mockito.when(countryGraph.hasCountry("for")).thenReturn(Boolean.FALSE);
        Mockito.when(countryGraph.hasCountry("to")).thenReturn(Boolean.FALSE);
        //when
        Optional<List<String>> result = routeService.calculateRoute("for", "to");
        //then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void emptyRoutePathReturnedTest() {
        //given
        Mockito.when(countryGraph.hasCountry("for")).thenReturn(Boolean.TRUE);
        Mockito.when(countryGraph.hasCountry("to")).thenReturn(Boolean.TRUE);
        Mockito.when(countryGraph.getRoute("for", "to")).thenReturn(Collections.emptyList());
        //when
        Optional<List<String>> result = routeService.calculateRoute("for", "to");
        //then
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void routePathCalculated() {
        //given
        List<String> path = Arrays.asList("c1", "c2", "c3");
        Mockito.when(countryGraph.hasCountry("for")).thenReturn(Boolean.TRUE);
        Mockito.when(countryGraph.hasCountry("to")).thenReturn(Boolean.TRUE);
        Mockito.when(countryGraph.getRoute("for", "to")).thenReturn(path);
        //when
        Optional<List<String>> result = routeService.calculateRoute("for", "to");
        //then
        Assertions.assertThat(result).containsSame(path);
    }
}
