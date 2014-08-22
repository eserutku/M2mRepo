package uk.co.kayratech.m2m.platform.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public interface LovValueDao extends JpaRepository<LovValue, String>,
		QueryDslPredicateExecutor<LovValue> {
	public <T extends LovValue> List<T> findByFrozen(Boolean frozen);

	public <T extends LovValue> List<T> findByLovTypeAndLic(LovType lovType, String lic);
}
