package com.salvatore.autoriBlog.repositories;

import com.salvatore.autoriBlog.entities.Autore;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface AutoriRepository extends JpaRepository<Autore, Long> {
    Optional<Autore> findByEmail(String email);

    boolean existsByEmail(String email);

}
