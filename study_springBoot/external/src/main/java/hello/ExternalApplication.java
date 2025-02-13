package hello;

import hello.external.CommandLineV2;
import hello.external.JavaSystemProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalApplication.class, args);

        // OsEnv.print();
        // JavaSystemProperties.print();
        // CommandLineV1.print(args);
        // CommandLineV2.print(args);
    }
}