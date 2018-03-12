package ibg.com.microservices.demo;

import ibg.com.microservices.demo.api.infra.TomcatShutdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Customize servlet container so that we could stop Tomcat when requested.

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(TomcatShutdown tomcatShutdown) {
        return container -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                ((TomcatEmbeddedServletContainerFactory)container).addContextCustomizers(tomcatShutdown::setContext);
            }
        };
    }

    @Bean
    public TomcatShutdown tomcatShutdown() {
        return new TomcatShutdown();
    }

}
