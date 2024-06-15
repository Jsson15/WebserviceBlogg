package com.example.webservice;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserviceApplication {
    public WebserviceApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
        System.out.println("Välkommen till Bloggheaven");
    }
}

