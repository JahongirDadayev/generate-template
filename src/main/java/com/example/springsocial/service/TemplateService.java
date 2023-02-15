package com.example.springsocial.service;

import com.example.springsocial.model.request.WordRequestDto;

import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface TemplateService {
    void generateTemplate(WordRequestDto requestDto, HttpServletResponse response) throws IOException, WriterException;
}
