package com.acelera.tcc.group03.dataload;

public class CustomerAccountDataLoad {
	private static String[][] customerAccountsData = {
			{"1","1","0.0"},
			{"2","2","200.0"},
			{"3","3","1500.0"},
			{"4","1","1000.0"},
			{"5","2","4000.0"},
			{"6","3","300.0"}
	};
	
	public static String[][] getCustomerAccountsData() {
		return CustomerAccountDataLoad.customerAccountsData;
	}
}