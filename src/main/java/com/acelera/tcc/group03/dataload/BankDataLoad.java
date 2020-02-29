package com.acelera.tcc.group03.dataload;

public class BankDataLoad {
	private static String[][] banksData = {
			{"Banco do Brasil","001"},
			{"Caixa Econômica Federal","104"},
			{"Itaú","341"},
			{"Bradesco","237"},
			{"Santander","044"}
	};
	
	public static String[][] getBanksData() {
		return BankDataLoad.banksData;
	}
}