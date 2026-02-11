package com.salvatore.autoriBlog.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    @GetMapping
    public String getExample1(){
        return "Ciao sono l'endpoint che risponde alle richieste GET su http://localhost:3000/examples";
    }

    @PostMapping
    public String postExample1() {
        return "Ciao sono l'endpoint che risponde alle richieste POST su http://localhost:3000/examples";
    }

    @GetMapping("/getExample")
    public String getExample() {
        return "Ciao sono l'endpoint che risponde alle richieste GET su http://localhost:3000/examples/getExample";
    }

    @PostMapping("/postExample")
    public String postExample(){
        return "Ciao sono l'endpoint che risponde alle richieste POST su http://localhost:3000/examples/postExample";
    }

    @PutMapping("/putExample")
    public String putExample() {
        return "Ciao sono l'endpoint che risponde alle richieste PUT su http://localhost:3000/examples/putExample";
    }

    @PatchMapping("/patchExample")
    public String patchExample() {
        return "Ciao sono l'endpoint che risponde alle richieste PATCH su http://localhost:3000/examples/patchExample";
    }

    @DeleteMapping("/deleteExample")
    public String deleteExample() {
        return "Ciao sono l'endpoint che risponde alle richieste DELETE su http://localhost:3000/examples/deleteExample";
    }

}
