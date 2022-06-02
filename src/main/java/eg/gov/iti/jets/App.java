package eg.gov.iti.jets;

import eg.gov.iti.jets.persistence.dao.BranchDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BranchDao branchDao){
        return ars->{
        };
    }
}
