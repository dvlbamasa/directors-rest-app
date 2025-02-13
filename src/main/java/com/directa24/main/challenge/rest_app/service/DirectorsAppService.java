package com.directa24.main.challenge.rest_app.service;

import com.directa24.main.challenge.rest_app.client.WireMockClient;
import com.directa24.main.challenge.rest_app.model.ClientResponseDto;
import com.directa24.main.challenge.rest_app.model.MovieDataDto;
import com.directa24.main.challenge.rest_app.model.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorsAppService {

    private final WireMockClient wireMockClient;

    public ResponseDto getDirectorsByThreshold(Integer threshold) {
        List<MovieDataDto> allMovies = new ArrayList<>();
        int page = 1;

        ClientResponseDto responseDto = wireMockClient.getMoviesByPage(page);
        allMovies.addAll(responseDto.getData());
        int totalPages = responseDto.getTotalPages();
        page++;

        while (page <= totalPages) {
            ClientResponseDto response = wireMockClient.getMoviesByPage(page);
            allMovies.addAll(response.getData());
            page++;
        }

        Set<String> directors = allMovies.stream()
                .collect(Collectors.groupingBy(MovieDataDto::getDirector, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(TreeSet::new));

        return ResponseDto.builder().directors(directors).build();
    }
}
