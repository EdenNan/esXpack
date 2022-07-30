package com.weaver.esxpack.controller;

import com.weaver.esxpack.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("con")
public class HelloWordController {

    @Autowired
    EsService esService;

    @RequestMapping("test")
    public String hello() {
        return "hello Word !";
    }

    @RequestMapping("creIndex")
    public Boolean creIndex() throws IOException {
        return esService.createUserIndex("user");
    }

}

