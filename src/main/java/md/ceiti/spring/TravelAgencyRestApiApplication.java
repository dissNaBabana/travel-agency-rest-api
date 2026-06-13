package md.ceiti.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelAgencyRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyRestApiApplication.class, args);
    }

    @PostConstruct
    public void printEnv() {
        System.out.println("=== ENVIRONMENT VARIABLES DIAGNOSTIC ===");
        System.out.println("SPRING_DATASOURCE_URL = " + System.getenv("SPRING_DATASOURCE_URL"));
        System.out.println("DATABASE_URL = " + System.getenv("DATABASE_URL"));
        System.out.println("SPRING_DATASOURCE_USERNAME = " + System.getenv("SPRING_DATASOURCE_USERNAME"));
        System.out.println("SPRING_DATASOURCE_PASSWORD = " + (System.getenv("SPRING_DATASOURCE_PASSWORD") != null ? "******" : "null"));
        System.out.println("=========================================");
    }
}
