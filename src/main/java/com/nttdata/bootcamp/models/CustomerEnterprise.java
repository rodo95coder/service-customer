package com.nttdata.bootcamp.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.models.product.ProductEnterprise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_enterprises")
@Builder
public class CustomerEnterprise {
	@Id
	private String id;
	private List<String> ownerNames;
	private List<String> authorizedSigners;
	private String rucDocument;
	private ProductEnterprise productEnterprise;

}
