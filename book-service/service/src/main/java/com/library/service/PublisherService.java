package com.library.service;

import com.library.dto.publisher.PublisherRequestDto;
import com.library.dto.publisher.PublisherResponseDto;
import com.library.dto.publisher.PublisherUpdateRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 05/12/2022
 */
public interface PublisherService {

    List<PublisherResponseDto> getAllPublisher(Integer pageNo, Integer pageSize, String sortBy);

    Optional<PublisherResponseDto> getPublisherById(UUID id);

    PublisherResponseDto createPublisher(PublisherRequestDto publisherRequestDto);

    PublisherResponseDto updatePublisher(UUID id, PublisherUpdateRequestDto publisherUpdateRequestDto);

    void deletePublisher(UUID id);

    PublisherResponseDto getPublisherByName(PublisherRequestDto publisherRequestDto);
}