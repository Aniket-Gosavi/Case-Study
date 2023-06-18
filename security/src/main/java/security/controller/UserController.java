package security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.exception.ResourceNotFoundException;
import security.proxy.UserProxy;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserProxy userproxy;
	
	@PostMapping(value = "/book",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> bookTrain(@RequestBody security.pojo.Booking book,@RequestHeader("Authorization") String authorization) {
		return userproxy.bookTrain(book, authorization);
	}

	@GetMapping(value = "/show",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> showTrain(@RequestHeader("Authorization") String authorization) {
		return userproxy.showTrain(authorization);
	}

	@GetMapping(value = "/findbyId/{id}",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> findById(@PathVariable int id,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException {
		return userproxy.findById(id,authorization);
	}
	
	@GetMapping(value = "/findbyname/{name}",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> findById(@PathVariable String name,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException {
		return userproxy.findByName(name,authorization);
	}
	
	@GetMapping(value = "/findbysourceanddestination/{source}/{destination}",produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> findBySourceAndDestination(@PathVariable String source, @PathVariable String destination,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException {
		return userproxy.findBySourceAndDestination(source, destination, authorization);
	}

}
