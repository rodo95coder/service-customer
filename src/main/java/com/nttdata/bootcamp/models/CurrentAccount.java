package com.nttdata.bootcamp.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentAccount {
	@Id
	private String id;
	private String accountingBalance;
	private String maintenance;
}
