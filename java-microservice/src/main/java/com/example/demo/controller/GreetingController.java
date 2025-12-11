package com.example.demo.controller;

import com.example.demo.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private final AtomicLong counter = new AtomicLong(1);

    @GetMapping("/")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        long requestId = counter.get();
        logger.info("Received GET / request - name={}, requestId={}", name, requestId);

        return new Greeting(
                counter.getAndIncrement(),
                String.format("Hello, %s! (from container-app)", name),
                Instant.now().toString()
        );
    }

    @PostMapping("/echo")
    public ResponseEntity<Greeting> echo(@RequestBody Greeting incoming) {

        long requestId = counter.get();
        logger.info("Received POST /echo request - content='{}', requestId={}", incoming.getContent(), requestId);

        Greeting response = new Greeting(
                counter.getAndIncrement(),
                incoming.getContent(),
                Instant.now().toString()
        );

        logger.info("Sending response for /echo - requestId={}, responseContent='{}'",
                requestId, response.getContent());

        return ResponseEntity.ok(response);
    }
}

