package com.rebizant.techassesment.web;

import com.rebizant.techassesment.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RouteService routeService;

    @Test
    void missingRouteTest() throws Exception {

        //given
        Mockito.when(routeService.calculateRoute("from", "to")).thenReturn(Optional.empty());

        //when
        //then
        mockMvc.perform(get("/api/route/from/to"))
                .andExpect(status().is(400));

    }

    @Test
    void validRouteTest() throws Exception {
        //given
        Mockito.when(routeService.calculateRoute("DEU", "UKR")).thenReturn(Optional.of(Arrays.asList("DEU", "POL", "UKR")));

        //when
        //then
        mockMvc.perform(get("/api/route/DEU/UKR"))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"route\": [\"DEU\",\"POL\",\"UKR\"]}"));
    }
}
