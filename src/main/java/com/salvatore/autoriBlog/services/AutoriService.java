package com.salvatore.autoriBlog.services;

import com.salvatore.autoriBlog.entities.Autore;
import com.salvatore.autoriBlog.exceptions.BadRequestException;
import com.salvatore.autoriBlog.exceptions.NotFoundException;
import com.salvatore.autoriBlog.payloads.NewAutorePayload;
import com.salvatore.autoriBlog.repositories.AutoriRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AutoriService {

    private final AutoriRepository autoriRepository;

    @Autowired
    public AutoriService(AutoriRepository autoriRepository) {
        this.autoriRepository = autoriRepository;
    }

    public Page<Autore> findAll(int page, int size, String orderBy, String sortCriteria){
        if(size > 100 || size < 0) size = 10;
        if(page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size,
                sortCriteria.equals("desc") ? Sort.by(orderBy).descending() : Sort.by(orderBy));
        return this.autoriRepository.findAll(pageable);
    }

    public Autore saveAutore(NewAutorePayload payload) {
        //1. verifico per email
        this.autoriRepository.findByEmail(payload.getEmail()).ifPresent(autore -> {
            throw new BadRequestException("l'email " + autore.getEmail() + " è già in uso");
        });

        //2. aggiungo campi
        Autore newAutore = new Autore(payload.getNome(), payload.getCognome(), payload.getEmail(), payload.getDataDiNascita());
        newAutore.setAvatar("https://ui-avatars.com/api?name=" + payload.getNome() + "+" + payload.getCognome());

        //3. salvo
        Autore savedAutore = this.autoriRepository.save(newAutore);

        //4. log
        log.info("l'utente con id " + savedAutore.getId() + " è stato salvato correttametne!");
        // torno l'utente salvato
        return savedAutore;
    }

    public Autore findById(long autoreId){
        return this.autoriRepository.findById(autoreId).orElseThrow(() -> new NotFoundException(autoreId));
    }

    public Autore findByIdAndUpdate(long autoreId, NewAutorePayload payload) {
        Autore found = this.findById(autoreId); // Riutilizzo il metodo sopra
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());
        found.setDataDiNascita(payload.getDataDiNascita());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.getNome() + "+" + payload.getCognome());
        return autoriRepository.save(found);
    }


    public void findByAndDelete(long autoreId) {
        Autore found = this.findById(autoreId);
        this.autoriRepository.delete(found);
    }
}
