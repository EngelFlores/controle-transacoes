package com.acelera.tcc.group03.repositories;

import com.acelera.tcc.group03.domains.Agency;

public class AgencyRepository extends Repository<Agency> {
	@Override
	protected String getSqlString() {
		return "SELECT a FROM Agency a";
	}
	
	@Override
	protected Class<Agency> getClassName() {
		return Agency.class;
	}
}