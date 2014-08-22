package uk.co.kayratech.m2m.platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import uk.co.kayratech.m2m.platform.model.AppConfigParameter;

public interface AppConfigParameterDao extends JpaRepository<AppConfigParameter, String>,
		QueryDslPredicateExecutor<AppConfigParameter> {

	public String findAppConfigParameterByParamValue(String paramValue);
}
