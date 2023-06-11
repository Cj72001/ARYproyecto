package com.ari.convertidor.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

	 private final ObjectMapper objectMapper;

	    public AppController(ObjectMapper objectMapper) {
	        this.objectMapper = objectMapper;
	    }

	    @PostMapping("/convert")
	    public String convertToJSON(@RequestBody String content) {
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            Object json = objectMapper.readValue(content, Object.class);
	            return objectMapper.writeValueAsString(json);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }}
