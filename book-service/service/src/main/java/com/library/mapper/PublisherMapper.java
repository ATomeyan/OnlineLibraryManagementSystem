package com.library.mapper;

import com.library.dto.publisher.PublisherRequestDto;
import com.library.dto.publisher.PublisherResponseDto;
import com.library.dto.publisher.PublisherUpdateRequestDto;
import com.library.entity.Publisher;
import org.springframework.stereotype.Component;

/**
 * @author Artur Tomeyan
 * @date 05/12/2022
 */
@Component
public final class PublisherMapper {

    private PublisherMapper() {
    }

    public PublisherResponseDto mapEntityToResponseDto(Publisher publisher) {
        PublisherResponseDto publisherResponseDto = new PublisherResponseDto();

        publisherResponseDto.setId(publisher.getId());
        publisherResponseDto.setName(publisher.getName());

        return publisherResponseDto;
    }

    public Publisher mapRequestDtoToEntity(PublisherRequestDto publisherRequestDto) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherRequestDto.getId());
        publisher.setName(publisherRequestDto.getName());

        return publisher;
    }

    public Publisher mapUpdateDtoToEntity(PublisherUpdateRequestDto publisherUpdateDto) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherUpdateDto.getId());
        publisher.setName(publisherUpdateDto.getName());

        return publisher;
    }
}