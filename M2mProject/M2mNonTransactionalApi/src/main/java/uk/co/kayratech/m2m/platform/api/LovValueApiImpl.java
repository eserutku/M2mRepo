package uk.co.kayratech.m2m.platform.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.model.lov.LovValue;
import uk.co.kayratech.m2m.platform.txlogic.BaseTxLogic;
import uk.co.kayratech.m2m.platform.txlogic.LovValueTxLogic;

@Component
public class LovValueApiImpl<T extends LovValue> extends BaseApiImpl<LovValue> implements LovValueApi {
	
	@Autowired
	private LovValueTxLogic txLogic;
	
	@Override
	@PreAuthorize("hasRole('ROLE_FIND_ALL_LOV_VALUES')")
	public List<LovValue> getAllLovValues() {
		return txLogic.findAllLovValues();
	}

	@Override
	protected BaseTxLogic<LovValue> getTxLogic() {
		return txLogic;
	}

}
