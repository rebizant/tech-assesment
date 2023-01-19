package com.rebizant.techassesment.graph;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class CountryGraphTest {

    private CountryGraph countryGraph = new CountryGraph();

    @BeforeEach
    void initMap() {
        countryGraph.addCountry("pol", Arrays.asList("BLR", "CZE", "DEU", "LTU", "RUS", "SVK", "UKR"));
        countryGraph.addCountry("jpn", Collections.emptyList());
        countryGraph.addCountry("blr", Arrays.asList("LVA", "LTU", "POL", "RUS", "UKR"));
        countryGraph.addCountry("cze", Arrays.asList("AUT", "DEU", "POL", "SVK"));
        countryGraph.addCountry("deu", Arrays.asList("AUT", "BEL", "CZE", "DNK", "FRA", "LUX", "NLD", "POL", "CHE"));
        countryGraph.addCountry("ltu", Arrays.asList("BLR", "LVA", "POL", "RUS"));
        countryGraph.addCountry("rus", Arrays.asList("AZE", "BLR", "CHN", "EST", "FIN", "GEO", "KAZ", "PRK", "LVA", "LTU", "MNG", "NOR", "POL", "UKR"));
        countryGraph.addCountry("svk", Arrays.asList("AUT", "CZE", "HUN", "POL", "UKR"));
        countryGraph.addCountry("ukr", Arrays.asList("BLR", "HUN", "MDA", "POL", "ROU", "RUS", "SVK"));
        countryGraph.addCountry("che", Arrays.asList("AUT", "FRA", "ITA", "LIE", "DEU"));
        countryGraph.addCountry("fra", Arrays.asList("AND", "BEL", "DEU", "ITA", "LUX", "MCO", "ESP", "CHE"));
        countryGraph.addCountry("aut", Arrays.asList("CZE", "DEU", "HUN", "ITA", "LIE", "SVK", "SVN", "CHE"));
        countryGraph.addCountry("usa", Arrays.asList("CAN", "MEX"));
        countryGraph.addCountry("esp", Arrays.asList("AND", "FRA", "GIB", "PRT", "MAR"));
        countryGraph.addCountry("ita", Arrays.asList("AUT", "FRA", "SMR", "SVN", "CHE", "VAT"));
        countryGraph.addCountry("nor", Arrays.asList("FIN", "SWE", "RUS"));
    }

    static Stream<Arguments> routesTestData() {
        return Stream.of(
                Arguments.of("deu", "ukr", Arrays.asList("DEU", "POL", "UKR")),
                Arguments.of("pol", "deu", Arrays.asList("POL", "DEU")),
                Arguments.of("pol", "jpn", Collections.emptyList()),
                Arguments.of("pol", "esp", Arrays.asList("POL", "DEU", "FRA", "ESP")),
                Arguments.of("pol", "deu", Arrays.asList("POL", "DEU")),
                Arguments.of("pol", "usa", Collections.emptyList()),
                Arguments.of("pol", "deu", Arrays.asList("POL", "DEU")),
                Arguments.of("pol", "ita", Arrays.asList("POL", "DEU", "FRA", "ITA")),
                Arguments.of("Ita", "NOR", Arrays.asList("ITA", "FRA", "DEU", "POL", "RUS", "NOR")),
                Arguments.of("aut", "fra", Arrays.asList("AUT", "DEU", "FRA")),
                Arguments.of("pol", "pol", Collections.emptyList()),
                Arguments.of("usa", "esp", Collections.emptyList()),
                Arguments.of("", "esp", Collections.emptyList()),
                Arguments.of("pol", null, Collections.emptyList()),
                Arguments.of("deu", " ", Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("routesTestData")
    public void checkRoutes(String from, String to, List<String> route) {
        Assertions.assertThat(countryGraph.getRoute(from, to)).containsExactlyElementsOf(route);
    }

    @Test
    public void checkCaseInsensitivityTest() {
        Assertions.assertThat(countryGraph.hasCountry("Pol")).isTrue();
        Assertions.assertThat(countryGraph.hasCountry("POL")).isTrue();
        Assertions.assertThat(countryGraph.hasCountry("pol")).isTrue();
    }

    @Test
    public void addNewCountryCaseInsensitiveTest() {
        //given
        CountryGraph graph = new CountryGraph();
        //when
        graph.addCountry("poL", Arrays.asList("DeU", "uKR"));
        //then
        Assertions.assertThat(graph.hasCountry("POL")).isTrue();
        Assertions.assertThat(graph.hasCountry("DEU")).isTrue();
        Assertions.assertThat(graph.hasCountry("UKR")).isTrue();
    }

    @Test
    public void addInvalidCountryCodeCountryNotAddedTest() {
        //given
        CountryGraph graph = new CountryGraph();
        //when
        graph.addCountry(" ", Arrays.asList("DEU", "UKR"));
        //then
        Assertions.assertThat(graph.hasCountry(" ")).isFalse();
    }
}
