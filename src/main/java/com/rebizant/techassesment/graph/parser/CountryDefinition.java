package com.rebizant.techassesment.graph.parser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDefinition {

    private final String code;
    private final List<String> neighbours = new ArrayList<>();

    @JsonCreator
    public CountryDefinition(@JsonProperty("cca3") String code, @JsonProperty("borders") List<String> neighbours) {
        this.code = code;
        this.neighbours.addAll(neighbours);
    }

    public String getCode() {
        return code;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }
}
