package tr.com.avea.rnd.om.datamodel.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import tr.com.avea.rnd.om.datamodel.dao.OmUserContactDao;

@Repository("omUserContactDao")
public class OmUserContactDaoImpl extends BaseDaoImpl implements OmUserContactDao {
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OmUserContact findByTcId(String tcId) {
		Criteria crit = getSession().createCriteria(OmUserContact.class);
		OmUserContact userContact = (OmUserContact) crit.add(Restrictions.eq("tcId", tcId)).uniqueResult();
		return userContact;
	}
	
}