package com.library.controller;

import com.library.dto.genre.GenreRequestDto;
import com.library.dto.genre.GenreResponseDto;
import com.library.dto.genre.GenreUpdateRequestDto;
import com.library.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Artur Tomeyan
 * @date 08/12/2022
 */
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAllGenres(@RequestParam(defaultValue = "0") Integer pageNo,
                                                               @RequestParam(defaultValue = "5") Integer pageSize,
                                                               @RequestParam(defaultValue = "name") String sortBy) {

        List<GenreResponseDto> allGenres = genreService.getAllGenres(pageNo, pageSize, sortBy);

        return ResponseEntity.status(HttpStatus.OK).body(allGenres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<GenreResponseDto>> getGenreById(@PathVariable UUID id){
        Optional<GenreResponseDto> byGenreId = genreService.getGenreById(id);

        return ResponseEntity.status(HttpStatus.OK).body(byGenreId);
    }

    @PostMapping
    public ResponseEntity<GenreResponseDto> createGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        GenreResponseDto createdGenre = genreService.createGenre(genreRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(createdGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDto> updateGenre(@PathVariable UUID id, @RequestBody @Valid GenreUpdateRequestDto genreUpdateRequestDto){
        GenreResponseDto updatedGenre = genreService.updateGenre(id, genreUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id){
        genreService.deleteGenre(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/genreByName")
    public ResponseEntity<GenreResponseDto> getGenreByName(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        GenreResponseDto genreByName = genreService.getGenreByName(genreRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(genreByName);
    }
}