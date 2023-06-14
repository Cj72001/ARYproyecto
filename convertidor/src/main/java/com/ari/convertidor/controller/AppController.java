package com.ari.convertidor.controller;
import com.ari.convertidor.model.ClienteJSON;
import com.ari.convertidor.model.ParametrosDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {
	 private List<ClienteJSON> listaDeClientes;
	 private final ObjectMapper objectMapper;

	    public AppController(ObjectMapper objectMapper) {
	        this.objectMapper = objectMapper;
	    }

	    @PostMapping("/convertToJson")
	    public ResponseEntity<List<ClienteJSON>> convertToJSON(@RequestBody String requestBody) {
	    	//Array para los obejtos clientes
	    	listaDeClientes = new ArrayList<>();
	    	
	    	//Array para separar los clientes
	  	  String[] clientesArrayTxtJson = new String[]{};
	    	
	    	//Array para separar el contenido del documento segun el delimitador TXT a JSON
	  	  String[] datosArrayTxtJson = new String[]{};
	    	
	  	  
	    	// Descomponer el objeto JSON recibido en un mapa de campos
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> jsonMap;
	        try {
	            jsonMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
	        } catch (IOException e) {
	            return ResponseEntity.badRequest().build();
	        }

	        //Obtener los valores de los campos del objeto JSON
	        String contenido = (String) jsonMap.get("content");
	        String llave = (String) jsonMap.get("key");
	        String delimitador = (String) jsonMap.get("delimit");
	        
	        
	        clientesArrayTxtJson = contenido.split("\n");
	        
	        
	        for (int i = 0; i < clientesArrayTxtJson.length; i++) {
	        	 String elemento = clientesArrayTxtJson[i];
	        	datosArrayTxtJson = elemento.split("\\"+delimitador);
	        	listaDeClientes.add(new ClienteJSON(datosArrayTxtJson[0], datosArrayTxtJson[1], datosArrayTxtJson[2], 
	        			datosArrayTxtJson[3], datosArrayTxtJson[4], datosArrayTxtJson[5], datosArrayTxtJson[6]));
	        }
	        	        

	        // Combinar los valores en un objeto Person
	        //ParametrosDTO parametros = new ParametrosDTO(contenido, llave, delimitador);

	        return ResponseEntity.ok(listaDeClientes);
	    }
	    

	    @PostMapping("/convertToTxt")
	    public ResponseEntity<List<ClienteJSON>> convertToTxt(@RequestBody String requestBody) {
	    	//Array para los obejtos clientes
	    	listaDeClientes = new ArrayList<>();
	    	
	  	  
	    	// Descomponer el objeto JSON recibido en un mapa de campos
	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> jsonMap;
	        try {
	            jsonMap = objectMapper.readValue(requestBody, new TypeReference<Map<String, Object>>() {});
	        } catch (IOException e) {
	            return ResponseEntity.badRequest().build();
	        }

	        //Obtener los valores de los campos del objeto JSON
	        String jsonContent = (String) jsonMap.get("content");
	        String llave = (String) jsonMap.get("key");
	        String delimitador = (String) jsonMap.get("delimit");
	        
	        Gson gson = new Gson();
	        List<ClienteJSON> listaDeClientes = gson.fromJson(jsonContent, new TypeToken<List<ClienteJSON>>() {}.getType());
	        	        	        

	        // Combinar los valores en un objeto Person
	        //ParametrosDTO parametros = new ParametrosDTO(contenido, llave, delimitador);

	        return ResponseEntity.ok(listaDeClientes);
	    }
	    
}
