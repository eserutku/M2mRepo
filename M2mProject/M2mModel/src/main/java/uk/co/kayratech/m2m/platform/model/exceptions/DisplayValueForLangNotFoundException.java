package uk.co.kayratech.m2m.platform.model.exceptions;

import uk.co.kayratech.m2m.platform.common.exceptions.M2MSystemException;
import uk.co.kayratech.m2m.platform.model.lov.LovType;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;
import uk.co.kayratech.m2m.platform.model.lov.enums.Language;

public class DisplayValueForLangNotFoundException extends M2MSystemException {

	private static final long serialVersionUID = -1732581163936675870L;

	public DisplayValueForLangNotFoundException(Language lang, LovType lovType, LovValue lovValue) {
		super("Display value for " + lovValue.getLic() + " of "
				+ lovType.getClass().getCanonicalName() + " is not found for language " + lang);
	}
}
