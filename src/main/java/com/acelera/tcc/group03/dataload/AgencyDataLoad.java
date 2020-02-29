package com.acelera.tcc.group03.dataload;

public class AgencyDataLoad {
	private static String[][] agenciesData = {
			{"1","Central","0100"},
			{"2","Matriz","2000"},
			{"3","Siqueira Campos","3250"},
	};
	
	public static String[][] getAgenciesData() {
		return AgencyDataLoad.agenciesData;
	}
}