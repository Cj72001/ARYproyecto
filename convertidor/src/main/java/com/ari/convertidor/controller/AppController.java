package com.ari.convertidor.controller;
import com.ari.convertidor.model.ParametrosDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	    public ResponseEntity<ParametrosDTO> convertToJSON(@RequestBody String requestBody) {
	    	// Descomponer el objeto JSON recibido en un mapa de campos
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> jsonMap;
	        try {
	            jsonMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
	        } catch (IOException e) {
	            return ResponseEntity.badRequest().build();
	        }

	        // Obtener los valores de los campos del objeto JSON
	        String contenido = (String) jsonMap.get("content");
	        String llave = (String) jsonMap.get("key");
	        String delimitador = (String) jsonMap.get("delimit");

	        // Combinar los valores en un objeto Person
	        ParametrosDTO parametros = new ParametrosDTO(contenido, llave, delimitador);

	        return ResponseEntity.ok(parametros);
	    }}
