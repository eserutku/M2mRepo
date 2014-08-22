package uk.co.kayratech.m2m.platform.api;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.dao.PermissionDao;
import uk.co.kayratech.m2m.platform.dao.RoleDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/m2mApiAppContext.xml" })
public class M2mNonTxApiIntegrationBaseTest {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PermissionDao permissionDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// TODO: Change this once Spring security and principal is wired up
		InheritableThreadLocalContext.instance.get().setUsername("TEST_USER");
		InheritableThreadLocalContext.instance.get().setLocale(new Locale("en"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		permissionDao.deleteAll();
		userDao.deleteAll();
		roleDao.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
		permissionDao.deleteAll();
		roleDao.deleteAll();
		userDao.deleteAll();
	}

}
