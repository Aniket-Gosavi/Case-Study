package security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.exception.ResourceNotFoundException;

import mailservice.EmailServiceImpl;
import security.pojo.TrainDetails;
import security.proxy.AdminProxy;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminProxy adminproxy;
	
	@Autowired
	private EmailServiceImpl esi;


	@GetMapping(value = "/show", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<TrainDetails>> viewAll(@RequestHeader("Authorization") String authorization) {
		return adminproxy.viewAll(authorization);
	}
	
	@PostMapping(value = "/addtrain", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addTrain(@RequestBody TrainDetails td,
			@RequestHeader("Authorization") String authorization) {
		esi.sendSimpleMail("saratehrishikesh17@gmail.com", "This is a testing mail sent form Spring boot app", "testing");
		return adminproxy.addtrain(td, authorization);
	}
	
	@PutMapping(value = "/updatetrain/{id}/{destination}" , produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateTrain(@PathVariable int id, @PathVariable String destination,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException{
		return adminproxy.updateTrain(id, destination, authorization);
	}

	@DeleteMapping(value = "/delete/{id}",produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteTrain(@PathVariable int id,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException{
		return adminproxy.deleteTrain(id,authorization);
	}
	

}
