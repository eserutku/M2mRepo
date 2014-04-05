package uk.co.kayratech.m2m.platform.txlogic.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import uk.co.kayratech.m2m.platform.common.log.InjectLogger;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;
import uk.co.kayratech.m2m.platform.txlogic.LovValueTxLogic;

public class TestLovValueTxLogic extends M2mTxLogicBaseIntegrationTest {

	@InjectLogger Logger logger;
	
	@Autowired
	private LovValueTxLogic<LovValue> classUnderTest;
	
	@Test
	public void testLovValuesCanBeRetrieved() {
		List<LovValue> lovValues = classUnderTest.getAllLovValues();
		
		assertNotNull(lovValues);
		assertTrue(lovValues.size() > 0);
	}

	@Test
	public void testLogger() {
		logger.debug("******************* DEBUG LOG *******************");
		logger.info("******************* INFO LOG *******************");
		logger.warn("******************* WARN LOG *******************");
		logger.error("******************* ERROR LOG *******************");
	}
}
