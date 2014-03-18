package uk.co.kayratech.m2m.platform.dao.integration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.dao.integration.support.UserTxSupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationConfiguration.xml" })
public class M2mDaoBaseIntegrationTest {

	@Autowired
	private UserTxSupport userSupport;
	
	@BeforeClass
	public static void beforeClass() {
		// TODO: Change this once Spring security and principal is wired up
		InheritableThreadLocalContext.instance.get().setUsername("TEST_USER");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userSupport.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}
}
