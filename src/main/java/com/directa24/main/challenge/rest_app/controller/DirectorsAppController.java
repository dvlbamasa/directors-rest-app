package com.directa24.main.challenge.rest_app.controller;

import com.directa24.main.challenge.rest_app.model.ResponseDto;
import com.directa24.main.challenge.rest_app.service.DirectorsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorsAppController {

    private final DirectorsAppService directorsAppService;

    @GetMapping
    public ResponseEntity<ResponseDto> getDirectorsByThreshold(@RequestParam("threshold") Integer threshold) {
        return ResponseEntity.ok().body(directorsAppService.getDirectorsByThreshold(threshold));
    }
}
