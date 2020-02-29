package com.acelera.tcc.group03.dataload;

public class TransactionTypeDataLoad {
	private static String[][] transactionTypesData = {
			{ "Cash deposit", "CREDIT" },
			{ "Check deposit", "CREDIT" },
			{ "Transfer from other account", "CREDIT" },
			{ "Transfer from other bank", "CREDIT" },
			{ "International deposit", "CREDIT" },
			{ "Cash withdraw", "DEBIT" },
			{ "Cashed check", "DEBIT" },
			{ "Bill payment", "DEBIT" },
			{ "Bill scheduled payment", "DEBIT" },
			{ "International shipping", "DEBIT" },
			{ "Federal Tax", "DEBIT" }
	};
	
	public static String[][] getTransactionTypesData() {
		return TransactionTypeDataLoad.transactionTypesData;
	}
}