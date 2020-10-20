package com.example.cors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class ExampleController {

    //simplest example
    @GetMapping
    public List<String> getAllCars() {
        return Arrays.asList("car1", "car2", "car3");
    }
}
