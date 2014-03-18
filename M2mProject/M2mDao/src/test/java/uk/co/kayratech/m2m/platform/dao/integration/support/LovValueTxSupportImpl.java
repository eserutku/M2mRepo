package uk.co.kayratech.m2m.platform.dao.integration.support;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;

@Component
public class LovValueTxSupportImpl extends BaseTxSupportImpl<LovValue> implements LovValueTxSupport {
	
	@Autowired
	private LovValueDao dao;
	
	@PostConstruct
	public void postConstruct() {
		super.setDao(dao);
	}
}
