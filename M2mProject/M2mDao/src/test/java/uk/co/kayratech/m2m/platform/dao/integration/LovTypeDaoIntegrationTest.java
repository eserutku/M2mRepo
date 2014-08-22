package uk.co.kayratech.m2m.platform.dao.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.model.lov.AuthenticationStatus;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public class LovTypeDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private LovTypeDao classUnderTest;
	@Autowired
	private LovValueDao lovValueDao;
	
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
	public void testFindLovTypeByLovValue() throws M2MBusinessException {
		PageRequest request = new PageRequest(0, 1);
		LovValue lovValue = lovValueDao.findAll(request).getContent().get(0);
		
		List<LovType> lovTypes = classUnderTest.findLovTypeByValues(lovValue);
		
		assertNotNull(lovTypes);
		assertTrue(lovTypes.size() == 1);
		assertEquals(AuthenticationStatus.class.getSimpleName(), lovTypes.get(0).getLovType());
		assertTrue(lovTypes.get(0).getValues().iterator().next().getClass().equals(AuthenticationStatus.class));
	}

}
