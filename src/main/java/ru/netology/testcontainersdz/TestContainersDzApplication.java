package ru.netology.testcontainersdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class TestContainersDzApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestContainersDzApplication.class, args);
    }
    @GetMapping("/")
    public String hello() {
        return "prodapp";
    }
}
