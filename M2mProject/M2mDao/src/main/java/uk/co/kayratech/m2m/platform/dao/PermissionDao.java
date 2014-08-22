package uk.co.kayratech.m2m.platform.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import uk.co.kayratech.m2m.platform.model.security.Permission;
import uk.co.kayratech.m2m.platform.model.security.Role;

public interface PermissionDao extends JpaRepository<Permission, String>,
		QueryDslPredicateExecutor<Permission> {
	@Query("Select perm from Permission perm inner join perm.parentRoles role where role in (:roles)")
	public List<Permission> findByRoles(@Param("roles") Collection<Role> roles);
}
