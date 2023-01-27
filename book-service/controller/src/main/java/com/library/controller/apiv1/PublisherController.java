package com.library.controller.apiv1;

import com.library.dto.publisher.PublisherRequestDto;
import com.library.dto.publisher.PublisherResponseDto;
import com.library.dto.publisher.PublisherUpdateRequestDto;
import com.library.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 06/12/2022
 */
@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> getAllPublishers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(defaultValue = "name") String sortBy) {
        List<PublisherResponseDto> allPublisher = publisherService.getAllPublisher(pageNo, pageSize, sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(allPublisher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PublisherResponseDto>> getPublisherById(@PathVariable UUID id){
        Optional<PublisherResponseDto> publisherById = publisherService.getPublisherById(id);

        return ResponseEntity.status(HttpStatus.OK).body(publisherById);
    }

    @PostMapping
    public ResponseEntity<PublisherResponseDto> createPublisher(@RequestBody @Valid PublisherRequestDto publisherRequestDto) {
        PublisherResponseDto createdPublisher = publisherService.createPublisher(publisherRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(createdPublisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> updatePublisher(@PathVariable UUID id, @RequestBody @Valid PublisherUpdateRequestDto publisherUpdateRequestDto){
        PublisherResponseDto updatedPublisher = publisherService.updatePublisher(id, publisherUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable UUID id){
        publisherService.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/publisherByName")
    public ResponseEntity<PublisherResponseDto> getPublisherByName(@RequestBody @Valid PublisherRequestDto publisherRequestDto) {
        PublisherResponseDto publisherByName = publisherService.getPublisherByName(publisherRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(publisherByName);
    }
}