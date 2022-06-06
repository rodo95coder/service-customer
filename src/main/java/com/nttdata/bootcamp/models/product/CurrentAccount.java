package com.nttdata.bootcamp.models.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentAccount {
	private String id;
	private String idCustomer;
	private String typeCustomer;
	private String accountingBalance;
	private String maintenance;
	private String profile;
}