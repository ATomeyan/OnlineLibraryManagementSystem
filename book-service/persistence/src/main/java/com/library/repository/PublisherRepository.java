package com.library.repository;

import com.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 05/12/2022
 */
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

    Optional<Publisher> findPublisherByName(String name);
}