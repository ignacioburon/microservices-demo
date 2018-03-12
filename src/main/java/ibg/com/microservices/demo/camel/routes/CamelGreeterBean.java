package ibg.com.microservices.demo.camel.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("greeterBean")
public class CamelGreeterBean {

    @Value("${greeting}")
    private String say;

    public String salute() {
        return say;
    }

}