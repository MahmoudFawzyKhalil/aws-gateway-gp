package eg.gov.iti.jets.persistence.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * A dummy example for configuring the beans to be used by spring CDI
 */

@Configuration
@ComponentScan(basePackages = "eg.gov.iti.jets.persistence")
public class DummyConfig {

    /**
     * Replace the return type for the bean with the interface defined in the service layer
     */


}
