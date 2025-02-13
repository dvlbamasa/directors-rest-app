package com.directa24.main.challenge.rest_app;

import com.directa24.main.challenge.rest_app.client.WireMockClient;
import com.directa24.main.challenge.rest_app.model.ClientResponseDto;
import com.directa24.main.challenge.rest_app.model.MovieDataDto;
import com.directa24.main.challenge.rest_app.model.ResponseDto;
import com.directa24.main.challenge.rest_app.service.DirectorsAppService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DirectorsApplicationTests {

    @Mock
    private WireMockClient wireMockClient;

    private DirectorsAppService directorsAppService;

    @BeforeEach
    void init() {
        directorsAppService = new DirectorsAppService(wireMockClient);
    }

    @Nested
    class GetDirectorsByThreshold {

        @Test
        void whenDataExceedThresholdThenSuccessResponse() {
            when(wireMockClient.getMoviesByPage(anyInt())).thenReturn(generateClientResponseDto());
            ResponseDto responseDto = directorsAppService.getDirectorsByThreshold(1);
            Assertions.assertNotNull(responseDto);
            Assertions.assertEquals(1, responseDto.getDirectors().size());
            verify(wireMockClient, atLeastOnce()).getMoviesByPage(anyInt());
        }

        @Test
        void whenDataDoesNotExceedThresholdThenSuccessResponse() {
            when(wireMockClient.getMoviesByPage(anyInt())).thenReturn(generateEmptyClientResponseDto());
            ResponseDto responseDto = directorsAppService.getDirectorsByThreshold(4);
            Assertions.assertEquals(0, responseDto.getDirectors().size());
            verify(wireMockClient, atLeastOnce()).getMoviesByPage(anyInt());
        }
    }

    private ClientResponseDto generateClientResponseDto() {
        return ClientResponseDto.builder()
                .data(Arrays.asList(generateMovieDataDto1(), generateMovieDataDto2()))
                .totalPages(3)
                .page(1)
                .build();
    }

    private ClientResponseDto generateEmptyClientResponseDto() {
        return ClientResponseDto.builder()
                .data(new ArrayList<>())
                .totalPages(3)
                .page(1)
                .build();
    }

    private MovieDataDto generateMovieDataDto1() {
        return MovieDataDto.builder()
                .title("Title 1")
                .director("Director 1")
                .year("2011")
                .build();
    }

    private MovieDataDto generateMovieDataDto2() {
        return MovieDataDto.builder()
                .title("Title 2")
                .director("Director 1")
                .year("2012")
                .build();
    }
}
