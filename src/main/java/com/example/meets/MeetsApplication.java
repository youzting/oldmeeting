package com.example.meets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MeetsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MeetsApplication.class, args);
  }
}

