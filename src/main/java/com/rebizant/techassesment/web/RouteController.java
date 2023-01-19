package com.rebizant.techassesment.web;

import com.rebizant.techassesment.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping("/route/{origin}/{destination}")
    public ResponseEntity<Route> calculateRoute(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {

        return routeService.calculateRoute(origin, destination)
                .map(value -> ResponseEntity.ok(new Route(value)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
