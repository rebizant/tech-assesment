package com.rebizant.techassesment.graph;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class CountryGraph {

    private static final Logger log = LoggerFactory.getLogger(CountryGraph.class);

    private final Map<String, CountryNode> graph = new HashMap<>();

    public void addCountry(String code, List<String> neighbours) {
        if (StringUtils.isBlank(code)) {
            log.warn("country code is blank, will not be inserted into graph");
            return;
        }
        CountryNode country = graph.computeIfAbsent(code.trim().toUpperCase(), CountryNode::new);
        if (!CollectionUtils.isEmpty(neighbours)) {
            neighbours.forEach(neighbourCode -> addNeighbour(country, neighbourCode));
        }
    }

    public boolean hasCountry(String countryCode) {
        if (StringUtils.isBlank(countryCode)) {
            return false;
        }
        return graph.containsKey(countryCode.toUpperCase());
    }

    public List<String> getRoute(String origin, String destination) {
        if (!hasCountry(origin) || !hasCountry(destination)) {
            return Collections.emptyList();
        }
        String normalizedOrigin = origin.toUpperCase();
        String normalizedDestination = destination.toUpperCase();

        if (normalizedDestination.equals(normalizedOrigin)) {
            return Collections.emptyList();
        }

        CountryNode originNode = graph.get(normalizedOrigin);
        CountryNode destinationNode = graph.get(normalizedDestination);

        if (destinationNode.withoutNeighbours() || originNode.withoutNeighbours()) {
            return Collections.emptyList();
        }

        LinkedList<RouteStep> queue = new LinkedList<>();
        queue.add(new RouteStep(new ArrayList<>(), originNode));
        return findPath(destinationNode, queue);
    }

    private List<String> findPath(CountryNode destination, LinkedList<RouteStep> queue) {
        Set<CountryNode> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            RouteStep routeStep = queue.poll();
            visited.add(routeStep.getNode());
            Set<CountryNode> neighbours = routeStep.getNode().getNeighbours();
            if (neighbours.contains(destination)) {
                return new RouteStep(routeStep.getPath(), destination).getPath();
            } else {
                neighbours.stream()
                        .filter(node -> !visited.contains(node))
                        .forEach(node -> queue.add(new RouteStep(routeStep.getPath(), node)));
            }

        }
        return Collections.emptyList();
    }

    private void addNeighbour(CountryNode country, String neighbourCode) {
        if (StringUtils.isBlank(neighbourCode)) {
            log.warn("country code is blank, will not be inserted into graph");
            return;
        }
        CountryNode neighbourNode = graph.computeIfAbsent(neighbourCode.trim().toUpperCase(), CountryNode::new);
        country.addNeighbour(neighbourNode);
    }

    private static class RouteStep {

        private final List<String> path = new ArrayList<>();
        private final CountryNode node;

        public RouteStep(List<String> parentPath, CountryNode node) {
            path.addAll(parentPath);
            path.add(node.getCode());
            this.node = node;
        }

        public List<String> getPath() {
            return Collections.unmodifiableList(path);
        }

        public CountryNode getNode() {
            return node;
        }
    }

}
