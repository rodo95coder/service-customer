package com.nttdata.bootcamp.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPerson {
	private PersonalCredit personalCredit;
	private SavingAccount savingAccount;
	private CurrentAccount currentAccount;
	private List<FixedTermAccount> fixedTermAccounts;

}
