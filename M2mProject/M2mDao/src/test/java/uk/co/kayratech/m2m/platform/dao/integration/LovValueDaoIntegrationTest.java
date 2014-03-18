package uk.co.kayratech.m2m.platform.dao.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.dao.integration.support.LovValueTxSupport;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public class LovValueDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private LovValueDao classUnderTest;
	@Autowired
	private LovValueTxSupport txSupport;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@Test
	public void testFindAllLovValues() {
		Page<LovValue> page = classUnderTest.findAll(new PageRequest(0, 10));
		
		assertNotNull(page);
		assertTrue(page.getContent().size() > 0);
	}
}
