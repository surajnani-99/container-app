package com.example.demo.controller;
import com.example.demo.model.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class GreetingController {
    private final AtomicLong counter = new AtomicLong(1);

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.getAndIncrement(),
            String.format("Hello, %s! (from container-app)", name),
            Instant.now().toString());
    }

    @PostMapping("/echo")
    public ResponseEntity<Greeting> echo(@RequestBody Greeting incoming) {
        Greeting g = new Greeting(counter.getAndIncrement(),
            incoming.getContent(),
            Instant.now().toString());
        return ResponseEntity.ok(g);
    }
}