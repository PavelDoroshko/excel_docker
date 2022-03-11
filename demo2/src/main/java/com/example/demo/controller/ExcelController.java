package com.example.demo.controller;

import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.ExcelService;
import com.example.demo.service.api.IPersonService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.demo.util.HttpHeadersUtil.getAttachHeaders;



@RestController
@RequestMapping("/excel")
public class ExcelController {

    private final PersonMapper personMapper;
    private final IPersonService iPersonService;
    private final ExcelService excelService;

    public ExcelController(PersonMapper personMapper, IPersonService iPersonService, ExcelService excelService) {
        this.personMapper = personMapper;
        this.iPersonService = iPersonService;
        this.excelService = excelService;
    }


    @GetMapping("/read/{id}")
    public ResponseEntity<?> readOne(@PathVariable("id") Long id) throws IOException {
        Resource responseFile = excelService.export(id);


        return  ResponseEntity
                .ok()
                .contentLength(responseFile.contentLength())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .headers(getAttachHeaders("excel.xlsx"))
                .body(responseFile);
    }
}
