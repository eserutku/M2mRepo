package uk.co.kayratech.m2m.platform.api.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.api.M2mNonTxApiIntegrationBaseTest;
import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.dao.PermissionDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.factory.PermissionFactory;
import uk.co.kayratech.m2m.platform.model.factory.RoleFactory;
import uk.co.kayratech.m2m.platform.model.factory.UserFactory;
import uk.co.kayratech.m2m.platform.model.security.Permission;
import uk.co.kayratech.m2m.platform.model.security.Role;
import uk.co.kayratech.m2m.platform.txlogic.UserTxLogic;

public class M2mNonTxApiSecurityTest extends M2mNonTxApiIntegrationBaseTest {

	@Autowired
	private UserFactory userFactory;
	@Autowired
	private RoleFactory roleFactory;
	@Autowired
	private PermissionFactory permissionFactory;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private LovTypeDao lovTypeDao;
	
	@Autowired
	private UserTxLogic classUnderTest;
	
	@Test
	public void testUserRolesAndPermissionsCanBeRetrieved() {
		Permission permission1 = permissionFactory.getPopulatedInstanceWithSystemFields(Permission.class);
		Permission permission2 = permissionFactory.getPopulatedInstanceWithSystemFields(Permission.class);
		Permission permission3 = permissionFactory.getPopulatedInstanceWithSystemFields(Permission.class);
		
		Role role1 = roleFactory.getPopulatedInstanceWithSystemFields(Role.class);
		Role role2 = roleFactory.getPopulatedInstanceWithSystemFields(Role.class);
		
		User user = userFactory.getPopulatedInstanceWithSystemFields(User.class);
		
		role1.getPermissions().add(permission1);
		role1.getPermissions().add(permission2);
		role2.getPermissions().add(permission3);
		
		user.getRoles().add(role1);
		user.getRoles().add(role2);
		User persistedUser = userDao.save(user);
		
		User userRetrievedFromDb = userDao.findByUsername(persistedUser.getUsername());
		
		assertNotNull(userRetrievedFromDb);
		assertNotNull(userRetrievedFromDb.getRoles());
		assertEquals(2, userRetrievedFromDb.getRoles().size());

		Set<Permission> permissionsOfUser = new HashSet<>();
		permissionsOfUser.addAll(permissionDao.findByRoles(userRetrievedFromDb.getRoles()));
		
		assertEquals(3, permissionsOfUser.size());
	}

}
