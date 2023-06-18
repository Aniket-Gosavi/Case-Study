package security.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.aniket.exception.ResourceNotFoundException;

@FeignClient(value="User-Service",url="http://localhost:8002")
public interface UserProxy {
	
	@PostMapping(value = "/book" , produces = "application/json")
	public ResponseEntity<?> bookTrain(@RequestBody security.pojo.Booking book,@RequestHeader("Authorization") String authorization);

	@GetMapping(value = "/show" , produces = "application/json")
	public ResponseEntity<?> showTrain(@RequestHeader("Authorization") String authorization);

	@GetMapping(value = "/findbyId/{id}" ,produces = "application/json")
	public ResponseEntity<?> findById(@PathVariable int id,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException;
	
	@GetMapping(value = "/findbyname/{name}",produces = "application/json")
	public ResponseEntity<?> findByName(@PathVariable String name,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException;
	
	@GetMapping(value = "/findbysourceanddestination/{source}/{destination}", produces = "application/json")
	public ResponseEntity<?> findBySourceAndDestination(@PathVariable String source, @PathVariable String destination,@RequestHeader("Authorization") String authorization) throws ResourceNotFoundException;

}
