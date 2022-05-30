package eg.gov.iti.jets.persistence.config;

import eg.gov.iti.jets.persistence.dao.DummyDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A dummy example for configuring the beans to be used by spring CDI
 */

@Configuration
public class DummyConfig {

    /**
     * Replace the return type for the bean with the interface defined in the service layer
     */

    @Bean
    public DummyDaoImpl dummyDao() {
        return new DummyDaoImpl();
    }
}
