package com.nttdata.bootcamp.models.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalCredit {
	private String id;
	private String idCustomerPerson;
	private String accountingBalance;
	private String availableBalance;
	private String debt;
	private Long numMovement; 
}