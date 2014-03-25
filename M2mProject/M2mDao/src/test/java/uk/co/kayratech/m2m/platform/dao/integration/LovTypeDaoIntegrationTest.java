package uk.co.kayratech.m2m.platform.dao.integration;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MBusinessException;
import uk.co.kayratech.m2m.platform.dao.LovTypeDao;
import uk.co.kayratech.m2m.platform.dao.integration.support.LovValueTxSupport;
import uk.co.kayratech.m2m.platform.model.lov.AuthenticationStatus;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

public class LovTypeDaoIntegrationTest extends M2mDaoBaseIntegrationTest {

	@Autowired
	private LovTypeDao classUnderTest;
	@Autowired
	private LovValueTxSupport lovValueTxSupport;
	
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
		
		// TODO: New Transactional annotation with rollbackFor = Exception
		
		LovValue lovValue = lovValueTxSupport.findAny(AuthenticationStatus.class);
		
		List<LovType> lovTypes = classUnderTest.findLovTypeByValues(lovValue);
		
		assertNotNull(lovTypes);
		assertTrue(lovTypes.size() == 1);
		assertTrue(lovTypes.get(0).getChildren().equals(AuthenticationStatus.class));
	}

}
