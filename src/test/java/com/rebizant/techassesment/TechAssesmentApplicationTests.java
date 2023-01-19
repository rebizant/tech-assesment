package com.rebizant.techassesment;

import com.rebizant.techassesment.graph.CountryGraph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechAssesmentApplicationTests {

    @Autowired
    private CountryGraph countryGraph;

    @Test
    void contextLoads() {
        Assertions.assertThat(countryGraph).isNotNull();
    }

}
