package com.example.springsocial.controller;

import com.example.springsocial.model.request.WordRequestDto;
import com.example.springsocial.service.TemplateService;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/generate-template")
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping
    public void create(@RequestBody WordRequestDto requestDto, HttpServletResponse response) throws IOException, WriterException {
        templateService.generateTemplate(requestDto, response);
    }
}
