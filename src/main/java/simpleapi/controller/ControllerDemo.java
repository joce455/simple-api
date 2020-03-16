package simpleapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import simpleapi.mongo.ClientService;
import simpleapi.mongo.Cliente;

@RestController
public class ControllerDemo {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClientService clientService;

	@PostMapping(value = "/createClient")
	public ResponseEntity<String> crearCliente(@RequestBody Cliente client) {
		clientService.saveOrUpdateClient(client);
		return new ResponseEntity<String>("Client added successfully", HttpStatus.OK);
	}

	@PutMapping(value = "/editClient")
	public ResponseEntity<String> editCliente(@RequestBody Cliente client) {
		clientService.saveOrUpdateClient(client);
		return new ResponseEntity<String>("Client edited successfully", HttpStatus.OK);
	}

	@GetMapping(value = "/getClient")
	public Cliente getCliente(int dni) {
		Cliente c = clientService.findByDni(dni);
		if (c != null) {
			return c;
		}
		return null;

	}

	@GetMapping(value = "/getAllClients")
	public ResponseEntity<?> getAll() {

		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.findAll());

	}

	@DeleteMapping(value = "/deleteClient")
	public ResponseEntity<String> deleteCliente(int dni) {
		
		clientService.deleteClient(dni);
		return ResponseEntity.status(HttpStatus.OK).body("Client deleted");
		
	}

}
