package com.directa24.main.challenge.rest_app.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseDto {
    private List<String> directors;
}
