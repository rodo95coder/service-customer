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
	private String accountingBalance;
	private String maintenance;
}
