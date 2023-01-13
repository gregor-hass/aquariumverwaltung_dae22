package program;

/*
  Raindrops V1.1                                                                      JH, 1.1.2020
  Raindrops V1.1 -implements a very basic REST interface (+ db access via JDBC) for a (simulated)
                  humidity sensor.
                  Database: PostgreSQL v.11.x

 */

//getting started with spring boot https://spring.io/guides/gs/spring-boot/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
@SpringBootApplication(scanBasePackages = { "pl.controller", "pl.swagger", "program" })
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);

  }
}