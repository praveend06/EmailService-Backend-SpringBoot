package in.praveenlab.email.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Content {

	private String to;
	private String subject;
	private String body;

}
