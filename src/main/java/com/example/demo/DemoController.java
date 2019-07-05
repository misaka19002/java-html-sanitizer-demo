package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by admin on 2019/6/29 23:20:05.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    

    @GetMapping("/echo")
    public String echo(HttpServletRequest request, @RequestParam String query) {
        String paramMap = null;
        try {
            paramMap = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(request.getParameterMap());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return request.getQueryString()+ paramMap + new Date();
    }
    
    @GetMapping("/echo/query")
    public String echo(@RequestParam String query) {
        return query;
    }
    
    @PostMapping("/echoXssBody")
    public String echoXssBody(@RequestBody String body, @RequestParam String query) {
        return body;
    }
}
