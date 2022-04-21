package com.example.dem.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/example/")
@RequiredArgsConstructor
public class NewController {
    //    @SneakyThrows
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody
//    String create(@RequestPart(name = "file") MultipartFile file) {
//        String originalFilename = file.getOriginalFilename();
//        return originalFilename;
//    }
    @PostMapping(value="{name}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public  String create(@PathVariable ("name") String name,@RequestPart(name = "file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        return originalFilename;
    }

}
