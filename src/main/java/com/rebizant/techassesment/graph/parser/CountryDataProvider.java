package com.rebizant.techassesment.graph.parser;

import java.io.IOException;
import java.util.Collection;

public interface CountryDataProvider {

    Collection<CountryDefinition> getCountries() throws IOException;
}
