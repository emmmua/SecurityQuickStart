package com.emmmua.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PreAuthorize("@FivkEx.hasAuthority('system:dept:list')")
//    @PreAuthorize(value = "hasAuthority('system:dept:list123')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/testCors")
    public String testCors() {
        return "testCors";
    }
}