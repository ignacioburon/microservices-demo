package ibg.com.microservices.demo.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {

    public static final String ROUTE_NAME = "TIMER_ROUTE";

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}").routeId(ROUTE_NAME).transform().method("greeterBean", "salute")
                                                   .filter(simple("${body} contains 'foo'")).to("log:foo").end().to("stream:out");
    }


}
