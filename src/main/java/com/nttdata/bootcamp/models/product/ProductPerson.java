package com.nttdata.bootcamp.models.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPerson {
	private String idCustomerPerson;
	private PersonalCredit personalCredit;
	private SavingAccount savingAccount;
	private CurrentAccount currentAccount;
	private List<FixedTermAccount> fixedTermAccounts;

}
