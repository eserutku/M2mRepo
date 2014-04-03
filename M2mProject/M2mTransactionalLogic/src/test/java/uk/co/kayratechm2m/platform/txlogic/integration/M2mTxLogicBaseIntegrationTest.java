package uk.co.kayratechm2m.platform.txlogic.integration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/applicationConfiguration.xml" })
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class M2mTxLogicBaseIntegrationTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// TODO: Change this once Spring security and principal is wired up
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
