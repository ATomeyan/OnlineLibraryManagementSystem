package com.library.service.impl;

import ch.qos.logback.classic.Logger;
import com.library.dto.publisher.PublisherRequestDto;
import com.library.dto.publisher.PublisherResponseDto;
import com.library.dto.publisher.PublisherUpdateRequestDto;
import com.library.entity.Publisher;
import com.library.exceptions.EmptyException;
import com.library.exceptions.NotFoundException;
import com.library.exceptions.NotValidException;
import com.library.mapper.PublisherMapper;
import com.library.repository.PublisherRepository;
import com.library.service.PublisherService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Artur Tomeyan
 * @date 05/12/2022
 */
@Service
public class PublisherServiceImpl implements PublisherService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PublisherServiceImpl.class);
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    @Transactional
    public List<PublisherResponseDto> getAllPublisher(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        Page<Publisher> publisherList = publisherRepository.findAll(pageable);

        if (publisherList.isEmpty()) {
            LOGGER.error("The list of publishers is empty.");
            throw new EmptyException("The list of publishers is empty.");
        }

        return publisherList.stream().map(publisherMapper::mapEntityToResponseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<PublisherResponseDto> getPublisherById(UUID id) {

        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Publisher> publisherById = publisherRepository.findById(id);

        if (publisherById.isEmpty()) {
            LOGGER.error("The publisher is not found.");
            throw new NotFoundException("The publisher is not found.");
        }

        PublisherResponseDto publisherResponseDto = publisherMapper.mapEntityToResponseDto(publisherById.get());

        return Optional.of(publisherResponseDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PublisherResponseDto createPublisher(PublisherRequestDto publisherRequestDto) {

        if (publisherRequestDto == null) {
            LOGGER.error("Publisher info object can't be null.");
            throw new NotValidException("Publisher info object can't be null.");
        }

        Publisher publisher = publisherMapper.mapRequestDtoToEntity(publisherRequestDto);

        Publisher createdPublisher = publisherRepository.save(publisher);

        LOGGER.info("Publisher is successfully saved.");
        return publisherMapper.mapEntityToResponseDto(createdPublisher);
    }

    @Override
    @Transactional
    public PublisherResponseDto updatePublisher(UUID id, PublisherUpdateRequestDto publisherUpdateRequestDto) {
        if (id == null) {
            LOGGER.error("The passed id can't be null.");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Publisher> publisher = publisherRepository.findById(id);

        if (publisher.isEmpty()) {
            LOGGER.error("The publisher is not found.");
            throw new NotFoundException("The publisher is not found.");
        }

        Publisher updatedPublisher = publisherRepository.save(publisherMapper.mapUpdateDtoToEntity(publisherUpdateRequestDto));

        LOGGER.info("Publisher successfully updated.");
        return publisherMapper.mapEntityToResponseDto(updatedPublisher);
    }

    @Override
    @Transactional
    public void deletePublisher(UUID id) {
        if (id == null) {
            LOGGER.error("The passed id can't be null");
            throw new NotValidException("The passed id can't be null.");
        }

        Optional<Publisher> publisher = publisherRepository.findById(id);

        if (publisher.isEmpty()) {
            LOGGER.error("The publisher is not found.");
            throw new NotFoundException("The publisher is not found.");
        }

        publisher.ifPresent(publisherRepository::delete);
        LOGGER.info("Publisher successfully deleted");
    }

    @Override
    public PublisherResponseDto getPublisherByName(PublisherRequestDto publisherRequestDto) {

        if (publisherRequestDto == null) {
            LOGGER.error("Publisher can't be null.");
            throw new NotValidException("Publisher can't be null.");
        }

        String name = publisherRequestDto.getName();

        Optional<Publisher> publisherByName = publisherRepository.findPublisherByName(name);
        if (publisherByName.isEmpty()) {
            LOGGER.error("Publisher by name is not found.");
            throw new NotFoundException("Publisher by name is not found.");
        }

        return publisherMapper.mapEntityToResponseDto(publisherByName.get());
    }
}