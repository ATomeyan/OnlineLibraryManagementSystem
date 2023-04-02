package com.library.controller;

import com.library.dto.author.AuthorRequestDto;
import com.library.dto.author.AuthorResponseDto;
import com.library.dto.author.AuthorUpdateRequestDto;
import com.library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 28/11/2022
 */
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                          @RequestParam(defaultValue = "firstName") String sortBy) {

        List<AuthorResponseDto> allAuthors = authorService.getAllAuthors(pageNo, pageSize, sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(allAuthors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AuthorResponseDto>> getByAuthorId(@PathVariable UUID id) {
        Optional<AuthorResponseDto> authorById = authorService.getAuthorById(id);

        return ResponseEntity.status(HttpStatus.OK).body(authorById);
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto) {
        AuthorResponseDto createdAuthor = authorService.createAuthor(authorRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(createdAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable UUID id, @RequestBody @Valid AuthorUpdateRequestDto authorUpdateRequestDto) {
        AuthorResponseDto updatedAuthor = authorService.updateAuthor(id, authorUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/byAuthorName")
    public ResponseEntity<AuthorResponseDto> getAuthorByName(@RequestBody @Valid AuthorRequestDto authorRequestDto) {
        AuthorResponseDto authorByName = authorService.getAuthorByName(authorRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(authorByName);
    }
}