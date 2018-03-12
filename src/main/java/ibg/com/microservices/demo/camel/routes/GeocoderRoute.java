package ibg.com.microservices.demo.camel.routes;

import static org.apache.camel.model.rest.RestParamType.query;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.google.code.geocoder.model.GeocodeResponse;

/**
 * A simple Camel REST DSL route example using the Geocoder component and documented with Swagger
 */
@Component
public class GeocoderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // rest-dsl is also configured in the application.properties file

        rest("/geocoder").description("Geocoder REST service")
            .consumes("application/json")
            .produces("application/json")

            .get().description("Geocoder address lookup").outType(GeocodeResponse.class)
                .param().name("address").type(query).description("The address to lookup").dataType("string").endParam()
                .responseMessage().code(200).message("Geocoder successful").endResponseMessage()
                // call the geocoder to lookup details from the provided address
                .toD("geocoder:address:${header.address}");
    }

}