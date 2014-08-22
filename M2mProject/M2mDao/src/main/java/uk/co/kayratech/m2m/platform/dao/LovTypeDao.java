package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovTypeDao extends JpaRepository<LovType, String>,
		QueryDslPredicateExecutor<LovType> {
	public List<LovType> findLovTypeByValues(LovValue values);

	public LovType findLovTypeByLovType(String lovType);
}
