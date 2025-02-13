package com.directa24.main.challenge.rest_app.client;

import com.directa24.main.challenge.rest_app.model.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "wiremock", url = "https://wiremock.dev.eroninternational.com/")
public interface WireMockClient {

    @GetMapping(value = "/api/movies/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    ClientResponseDto getMoviesByPage(@RequestParam("page") Integer page);

}


