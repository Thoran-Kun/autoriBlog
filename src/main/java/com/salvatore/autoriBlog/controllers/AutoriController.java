package com.salvatore.autoriBlog.controllers;

import com.salvatore.autoriBlog.entities.Autore;
import com.salvatore.autoriBlog.payloads.NewAutorePayload;
import com.salvatore.autoriBlog.services.AutoriService;
// IMPORT CORRETTO: Deve essere org.springframework.data.domain
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autori")
public class AutoriController {

    private final AutoriService autoriService;

    @Autowired
    public AutoriController(AutoriService autoriService) {
        this.autoriService = autoriService;
    }

    @GetMapping
    public Page<Autore> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String orderBy
    ){
        return this.autoriService.findAll(page, size, orderBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAutore(@RequestBody NewAutorePayload payload){
        return this.autoriService.saveAutore(payload);
    }

    @GetMapping("/{autoreId}")
    public Autore getAutoreById(@PathVariable long autoreId){
        return this.autoriService.findById(autoreId);
    }

    @PutMapping("/{autoreId}")
    public Autore getAutoreByIdAndUpdate(@PathVariable long autoreId, @RequestBody NewAutorePayload payload){
        return this.autoriService.findByIdAndUpdate(autoreId, payload);
    }

    @DeleteMapping("/{autoreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getAutoreIdAndDelete(@PathVariable long autoreId){
        this.autoriService.findByAndDelete(autoreId);
    }
}
