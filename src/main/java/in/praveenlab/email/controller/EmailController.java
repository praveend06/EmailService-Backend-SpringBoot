package in.praveenlab.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.praveenlab.email.model.Content;
import in.praveenlab.email.model.EmailResponse;
import in.praveenlab.email.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody Content request){
		
		System.out.println(request);
	    boolean result = emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
	    
	    return (result)?
	    		ResponseEntity.ok(new EmailResponse("Email sent successfully!!")):
	    	ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent, Something went wrong!!"));
		
	}


}
