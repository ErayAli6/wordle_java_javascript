package com.fmi.wordle.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

@Controller
public class HomeController {

    @GetMapping(path = "/")
    @ResponseBody
    public String sayHello(HttpServletResponse res) {
        res.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        return "Hello 123 тест \uD83D\uDE00";
    }
}
