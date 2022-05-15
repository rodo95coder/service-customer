package com.nttdata.bootcamp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_persons")
@Builder
public class CustomerPerson {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String idDocument;
	private ProductPerson productPerson;
	
}
