package uk.co.kayratech.m2m.platform.model.lov;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uk.co.kayratech.m2m.platform.model.M2mModelBaseTest;
import uk.co.kayratech.m2m.platform.model.exceptions.DisplayValueForLangNotFoundException;
import uk.co.kayratech.m2m.platform.model.lov.enums.Language;

public class LovValueTest extends M2mModelBaseTest {

	private static final String DISPLAY_VALUE_EN = "Display Value English";
	private static final String DISPLAY_VALUE_TR = "Display Value Turkish";
	
	@Test
	public void testIfDisplayValueIsNullItIsFoundFromDisplayValueCollection() {
		TestLovValue lovValue = createDefaultTestLovValue();
		lovValue.setDisplayValue(null);
		
		String retrievedDisplayVal = lovValue.getDisplayValue();
		
		assertNotNull(retrievedDisplayVal);
		assertEquals(DISPLAY_VALUE_EN, retrievedDisplayVal);
	}

	@Test(expected = DisplayValueForLangNotFoundException.class)
	public void testIfDisplayValueIsNotFoundInCollectionExceptionIsThrown() {
		TestLovValue lovValue = createDefaultTestLovValue();
		lovValue.setDisplayValue(null);
		lovValue.getDisplayValues().clear();
		
		lovValue.getDisplayValue();
	}
	
	private TestLovValue createDefaultTestLovValue() {
		TestLovValue lovValue = new TestLovValue();
		
		LovDisplayValue displayValEng = new LovDisplayValue();
		displayValEng.setLovValue(lovValue);
		displayValEng.setDisplayValue(DISPLAY_VALUE_EN);
		displayValEng.setLanguage(Language.ENGLISH);
		
		LovDisplayValue displayValTr = new LovDisplayValue();
		displayValTr.setLovValue(lovValue);
		displayValTr.setDisplayValue(DISPLAY_VALUE_TR);
		displayValTr.setLanguage(Language.TURKISH);
		
		lovValue.getDisplayValues().add(displayValEng);
		lovValue.getDisplayValues().add(displayValTr);
		
		LovType lovType = new LovType();
		lovValue.setLovType(lovType);
		
		return lovValue;
	}
}
