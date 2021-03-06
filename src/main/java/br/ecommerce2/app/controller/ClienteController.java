package br.ecommerce2.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ecommerce2.app.model.*;
import br.ecommerce2.app.service.ClienteService;
import br.ecommerce2.app.spec.ClienteList;
import br.ecommerce2.app.spec.ClienteSpec;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends BaseController {
	
	private final ClienteService _service;
	
	@Autowired
	public ClienteController(ClienteService service) {
		_service = service;
	}
	
	@PostMapping
    public ResponseEntity inserir(@RequestBody ClienteSpec clienteSpec) {
		try {
			BaseResponse response = _service.inserir(clienteSpec);
			return ResponseEntity.status(response.StatusCode).body(response);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
    }
	
	@GetMapping(path = "/{id}")
    public ResponseEntity obter(@PathVariable Long id) {		
		try {
			Cliente response = _service.obter(id);
			return ResponseEntity.status(response.StatusCode).body(response);	
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}   	
    }

	@GetMapping
    public ResponseEntity listar() {		
		try {
			ClienteList clientes = _service.listar();  		
	    	return ResponseEntity.status(HttpStatus.OK).body(clientes);			
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);			
		}		
    }
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		try {
			BaseResponse response = _service.deletar(id);
			return ResponseEntity.status(response.StatusCode).build(); 
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity atualizar(@RequestBody ClienteSpec clienteSpec, @PathVariable Long id) {
		try {
			BaseResponse response = _service.atualizar(id, clienteSpec);
			return ResponseEntity.status(response.StatusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.StatusCode).body(errorBase);
		}
	}

}
