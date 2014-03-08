package uk.co.kayratech.m2m.platform.dao.integration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;

public class M2mDaoBaseIntegrationTest {

	@BeforeClass
	public static void beforeClass() {
		InheritableThreadLocalContext.instance.get().setUsername("TEST_USER");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
}