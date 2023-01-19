package com.rebizant.techassesment.graph;

import java.util.*;

class CountryNode {

    private final String code;
    private final Set<CountryNode> neighbours = new HashSet<>();

    CountryNode(String code) {
        Objects.requireNonNull(code, "Country code can't be null");
        this.code = code.toUpperCase();
    }

    void addNeighbour(CountryNode neighbour) {
        neighbours.add(neighbour);
    }

    Collection<CountryNode> getNeighbours() {
        return Collections.unmodifiableSet(neighbours);
    }

    boolean withoutNeighbours() {
        return neighbours.isEmpty();
    }

    String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryNode that = (CountryNode) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CountryNode{" +
                "code='" + code + '\'' +
                '}';
    }
}
