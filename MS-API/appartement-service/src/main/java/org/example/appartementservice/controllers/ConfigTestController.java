package org.example.appartementservice.controllers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
public class ConfigTestController {
    @Value("${global.params.a}")
    private int a;
    @Value("${global.params.b}")
    private int b;
    @Value("${appartement.params.p1}")
    private int p1;
    @Value("${appartement.params.p2}")
    private int p2;
    @GetMapping("/config")
    public Map<String,Integer> configTest() {
        return Map.of("a", a, "b", b, "p1", p1, "p2", p2);
    }
}
