package com.acelera.tcc.group03.dataload;

public class CustomerDataLoad {
	private static String[][] customersData = {
			{"Fábio Victor Pfeiff","INDIVIDUAL","012345678"},
			{"Peter Parker","INDIVIDUAL","111222333"},
			{"Tony Stark","INDIVIDUAL","123456789"},
			{"Bruce Banner","INDIVIDUAL","222333444"},
			{"Steve Rogers","INDIVIDUAL","333444555"},
			{"The Shield","EMPLOYER","013324880"}
	};
	
	public static String[][] getCustomersData() {
		return CustomerDataLoad.customersData;
	}
}