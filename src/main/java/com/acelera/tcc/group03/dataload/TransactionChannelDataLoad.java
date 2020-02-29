package com.acelera.tcc.group03.dataload;

public class TransactionChannelDataLoad {
	private static String[][] transactionChannelsData = {
			{ "Agência" },
			{ "Parceiro" },
			{ "ATM" },
			{ "Telefone" },
			{ "Internet" },
			{ "App" }
	};
	
	public static String[][] getTransactionChannelsData() {
		return TransactionChannelDataLoad.transactionChannelsData;
	}
}