package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import tr.com.avea.rnd.om.datamodel.dao.OmUserDao;

@Repository("omUserDao")
public class OmUserDaoImpl extends BaseDaoImpl implements OmUserDao {

	@Override
	public OmUser findUserwithUserName(String userName) {
		Criteria crit = getSession().createCriteria(OmUser.class);
		crit.setFetchMode("omResponsibilities", FetchMode.JOIN).setFetchMode("primaryPosition",
				FetchMode.JOIN).setFetchMode("dealerType", FetchMode.JOIN);
		crit.add(Restrictions.eq("userName", userName));
		crit.setFetchMode("primaryPosition.dealerType", FetchMode.JOIN);
		crit.setFetchMode("primaryPosition.positionStatus", FetchMode.JOIN);
		crit.setFetchMode("primaryPosition.superUser", FetchMode.JOIN);
		OmUser user = (OmUser) crit.uniqueResult();
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OmUser findByPrimaryKey(String id) {
		Criteria crit = getSession().createCriteria(OmUser.class);
		OmUser user = (OmUser) crit.add(Restrictions.eq("rowId", id)).uniqueResult();
		return user;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<OmPosition> getPositionsOfUserByUserId(String id, int offset, int limit) {
		limit = imposeMaxLimit(limit);
		Query query = em.createQuery(" SELECT u.omPositions FROM OmUser u WHERE u.rowId = :id").setParameter(
				"id", id);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<OmPosition> omPositions = query.getResultList();
		return omPositions;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OmUser findByPrimaryKeyWithEagerLovs(String id) {
		Criteria crit = getSession().createCriteria(OmUser.class);
		setUpEagerLovs(crit);
		OmUser user = (OmUser) crit.add(Restrictions.eq("rowId", id)).uniqueResult();
		return user;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OmUser findByUserIdEagerly(String userId) {
		Criteria crit = getSession().createCriteria(OmUser.class);
		setUpEagerLovs(crit);
		crit.setFetchMode("omPositions", FetchMode.JOIN);
		crit.setFetchMode("omResponsibilities", FetchMode.JOIN);
		OmUser user = (OmUser) crit.add(Restrictions.eq("userId", userId)).uniqueResult();
		return user;
	}
	
	private void setUpEagerLovs(Criteria crit) {
		crit.setFetchMode("userStatus", FetchMode.JOIN);
		crit.setFetchMode("salesRegion", FetchMode.JOIN);
		crit.setFetchMode("dealerType", FetchMode.JOIN);
	}
}
