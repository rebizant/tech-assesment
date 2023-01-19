package com.rebizant.techassesment.web;

import java.util.List;

public class Route {

    private List<String> route;

    public Route() {
    }

    public Route(List<String> route) {
        this.route = route;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }
}
