package br.com.cleanachitecture.application.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleanachitecture.application.resources.request.UserRequestDTO;
import br.com.cleanachitecture.application.resources.response.UserResponseDTO;
import br.com.cleanachitecture.application.services.interfaces.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> save(@RequestBody UserRequestDTO userRequest){
		return ResponseEntity.ok(service.save(userRequest));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UserRequestDTO userRequest){
		return ResponseEntity.ok(service.update(id, userRequest));
	}
	
	@PatchMapping(value = "active/{id}")
	public ResponseEntity<Void> active(@PathVariable Long id){
		service.activeUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "inactive/{id}")
	public ResponseEntity<Void> inactive(@PathVariable Long id){
		service.inactiveUser(id);
		return ResponseEntity.noContent().build();
	}
}
