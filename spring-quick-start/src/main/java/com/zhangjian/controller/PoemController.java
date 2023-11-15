package com.zhangjian.controller;

import com.zhangjian.service.PoemService;
import com.zhangjian.service.impl.PoemServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoemController {

    @Autowired
    private PoemService poemService;

    @GetMapping("getPoem")
    public String getPoem() {
        String content = poemService.addRight();
        return content;
    }
}
