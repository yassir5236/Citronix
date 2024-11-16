package org.yassir.citronix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CitronixApplication {

    public static void main(String[] args) {
        System.out.println("hello word");
        SpringApplication.run(CitronixApplication.class, args);
    }

}
