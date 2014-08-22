package uk.co.kayratech.m2m.platform.dao.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.dao.RoleDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.factory.PermissionFactory;
import uk.co.kayratech.m2m.platform.model.factory.RoleFactory;
import uk.co.kayratech.m2m.platform.model.factory.UserFactory;
import uk.co.kayratech.m2m.platform.model.security.Permission;
import uk.co.kayratech.m2m.platform.model.security.Role;

public class RoleDaoIntegrationTest extends M2mDaoBaseIntegrationTest {
	
	@Autowired
	private RoleDao classUnderTest;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleFactory factory;
	@Autowired
	private PermissionFactory permissionFactory;
	@Autowired
	private UserFactory userFactory;

	@Test
	public void testRoleCanBeSaved() {
		Permission permission1 = permissionFactory.getPopulatedInstanceWithSystemFields(Permission.class);
		Permission permission2 = permissionFactory.getPopulatedInstanceWithSystemFields(Permission.class);
		
		Role role = factory.getPopulatedInstanceWithSystemFields(Role.class);
		role.getPermissions().add(permission1);
		role.getPermissions().add(permission2);
		
		classUnderTest.save(role);
	}
}
