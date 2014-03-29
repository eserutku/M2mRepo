package uk.co.kayratech.m2m.platform.dao.integration.support;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.kayratech.m2m.platform.dao.LovValueDao;
import uk.co.kayratech.m2m.platform.model.lov.LovValue;
import uk.co.kayratech.m2m.platform.model.support.LovValueSupport;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class LovValueTxSupportImpl extends BaseTxSupportImpl<LovValue> implements LovValueTxSupport {
	
	@Autowired
	private LovValueDao dao;
	@Autowired
	private LovValueSupport support;
	
	@PostConstruct
	public void postConstruct() {
		super.setDao(dao);
		super.setSupport(support);
	}

	@Override
	public LovValue findAny(Class<? extends LovValue> lovValueClass) {
		String queryStr = "select e from " + lovValueClass.getSimpleName() + " e";
		TypedQuery<? extends LovValue> query = getEm().createQuery(queryStr, lovValueClass);
		query.setFirstResult(1);
		query.setMaxResults(1);
		
		List<? extends LovValue> values = query.getResultList();
		
		assert(values.size() > 0);
		
		return values.get(0);
	}
}
