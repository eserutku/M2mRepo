package uk.co.kayratech.m2m.platform.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import uk.co.kayratech.m2m.platform.model.User;

public interface UserDao extends JpaRepository<User, String>, QueryDslPredicateExecutor<User> {

	@EntityGraph(value = "UserWithRoles", type = EntityGraphType.LOAD)
	public User findByUsername(String username);
}
