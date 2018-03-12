package ibg.com.microservices.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {
    
    
    private GreetingProperties properties;

    @Autowired
    public GreetingController(GreetingProperties properties) {
        this.properties = properties;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(String.format(properties.getMessage(), name));
    }
    
}
