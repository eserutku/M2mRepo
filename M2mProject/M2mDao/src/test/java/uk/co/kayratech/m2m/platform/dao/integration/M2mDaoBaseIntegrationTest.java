package uk.co.kayratech.m2m.platform.dao.integration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.dao.UserDao;

public class M2mDaoBaseIntegrationTest {

	@Autowired
	private UserDao baseDaoInstance;
	private UserTxSupport txSupport;
	
	@BeforeClass
	public static void beforeClass() {
		InheritableThreadLocalContext.instance.get().setUsername("TEST_USER");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		txSupport = new UserTxSupportImpl(baseDaoInstance);
		txSupport.deleteAllTableData();
	}

	@After
	public void tearDown() throws Exception {
	}
}
