package ru.netology.testcontainersdz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestContainersDzApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);
    @Container
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void contextLoads() {
        Integer devappPort = devapp.getMappedPort(8080);
        Integer prodappPort = prodapp.getMappedPort(8081);

        ResponseEntity<String> forEntityDevapp = restTemplate.getForEntity("http://localhost:" + devappPort, String.class);
        ResponseEntity<String> forEntityProdapp = restTemplate.getForEntity("http://localhost:" + prodappPort, String.class);

        String port8080 = "devapp";
        String port8081 = "prodapp";

        assertEquals(port8080,forEntityDevapp.getBody());
        assertEquals(port8081,forEntityProdapp.getBody());

        System.out.println(forEntityDevapp.getBody());
        System.out.println(forEntityProdapp.getBody());

    }

}
