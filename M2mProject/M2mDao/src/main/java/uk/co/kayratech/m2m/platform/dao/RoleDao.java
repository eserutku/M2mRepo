package uk.co.kayratech.m2m.platform.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import uk.co.kayratech.m2m.platform.model.security.Role;

public interface RoleDao extends JpaRepository<Role, String>, QueryDslPredicateExecutor<Role> {
	
	// TODO: Due to HHH-9230 bug in Hibernate we can't use an 'in' query with an Entity Graph. Remove the 
	// query string method from permission dao and use this one instead when the bug is resolved
//	@EntityGraph(value = "RoleWithPermissions", type = EntityGraphType.LOAD)
//	public List<Role> findByTechnicalIdIn(Collection<String> techIds);
}
