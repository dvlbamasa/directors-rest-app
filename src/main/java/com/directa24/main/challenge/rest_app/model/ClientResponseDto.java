package com.directa24.main.challenge.rest_app.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientResponseDto {

    private int page;

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total_pages")
    private int totalPages;

    private int total;
    private List<MovieDataDto> data;

}
