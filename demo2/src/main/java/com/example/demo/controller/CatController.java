package com.example.demo.controller;

import com.example.demo.dto.CatDto;
import com.example.demo.model.Cat;
import com.example.demo.service.api.ICatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cat")
public class CatController {
    private final ObjectMapper objectMapper;
    private final ICatService iCatService;

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080/example/";

    @PostMapping(value="/example/")
    public String create() {
        File file=new File("d:/first.xlsx");
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String str ="pasha";
        System.out.println("контроллер demo2");
        System.out.println(file.getName());
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(baseUrl+str,requestEntity,String.class);

        String bod = responseEntity.getBody();
        return bod ;
    }
    @PostMapping("/create")
    public CatDto createCar(@RequestBody CatDto catDto) {
        Cat cat = objectMapper.convertValue(catDto, Cat.class);
        return objectMapper.convertValue(iCatService.create(cat), CatDto.class);
    }

    @GetMapping("/read/all")
    public List<CatDto> readAll() {
        return iCatService.readAll().stream()
                .map(cat -> objectMapper.convertValue(cat,CatDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/read/one")
    public CatDto readOne(@RequestParam("id") long id) {
        return objectMapper.convertValue(iCatService.readOne(id), CatDto.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        iCatService.deleteById(id);

    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        iCatService.deleteAll();
    }

    @PutMapping("/update")
    public CatDto update(@RequestParam(value = "id") Long id,
                         @RequestBody CatDto catDto) {
        Cat cat = objectMapper.convertValue(catDto, Cat.class);
        return objectMapper.convertValue(iCatService.update(id, cat), CatDto.class);
    }


}
