package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import tr.com.avea.rnd.om.datamodel.dao.OmResponsibilityDao;

@Repository("omResponsibilityDao")
public class OmResponsibilityDaoImpl extends BaseDaoImpl implements OmResponsibilityDao {

	@Override
	public List<OmResponsibility> selectUserResponsibilitiesWithViews(OmUser omUser) {
		
		Criteria crit = getSession().createCriteria(OmUser.class);
		crit.setFetchMode("omResponsibilities", FetchMode.JOIN).setFetchMode("omResponsibilities.omRespViews", FetchMode.JOIN).setFetchMode("omResponsibilities.omRespViews.omView", FetchMode.JOIN);
		OmUser user = (OmUser) crit.add(Restrictions.eq("userId", omUser.getUserId())).uniqueResult();
		Set<OmResponsibility> omResponsibilitiesSet = user.getOmResponsibilities();
		List<OmResponsibility> omResponsibilities = new ArrayList<OmResponsibility>(omResponsibilitiesSet);

	
/*		Query query = em.createQuery(" SELECT u.omResponsibilities FROM OmUser u " + " WHERE u = :omUser ")
				.setParameter("omUser", omUser);
		@SuppressWarnings("unchecked")
		List<OmResponsibility> omResponsibilities = query.getResultList();

		for (OmResponsibility omResponsibility : omResponsibilities) {
			List<OmRespView> omRespViewList = new ArrayList<OmRespView>(omResponsibility.getOmRespViews());
			for (OmRespView omRespView : omRespViewList) {
				query = em.createQuery(" SELECT rv.omView FROM OmRespView rv " + " WHERE rv = :omRespView ")
						.setParameter("omRespView", omRespView);
				query.getResultList();
			}
		}
*/
		return omResponsibilities;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OmResponsibility> selectMatchedResponsibilities(OmResponsibility omResponsibility) {
		Criteria criteria = getSession().createCriteria(omResponsibility.getClass());
		
		if(omResponsibility.getDealerGroup() == null) {
			criteria.add(Restrictions.isNull("dealerGroup"));
		} else {
			criteria.add(Restrictions.eq("dealerGroup", omResponsibility.getDealerGroup()));
		}
		
		if(omResponsibility.getDealerType() == null) {
			criteria.add(Restrictions.isNull("dealerType"));
		} else {
			criteria.add(Restrictions.eq("dealerType", omResponsibility.getDealerType()));
		}
		
		if(omResponsibility.getPositionType() == null) {
			criteria.add(Restrictions.isNull("positionType"));
		} else {
			criteria.add(Restrictions.eq("positionType", omResponsibility.getPositionType()));
		}
		
		if(omResponsibility.getIsInternal() == null) {
			criteria.add(Restrictions.isNull("isInternal"));
		} else {
			criteria.add(Restrictions.eq("isInternal", omResponsibility.getIsInternal()));
		}
		
		return criteria.list();
	}
	
	@Override
	public List<OmResponsibility> selectdefaultResponsibilities(List<AppConfig> appConfigList) {
		List<OmResponsibility> omResponsibilityList = new ArrayList<OmResponsibility>();
		for (AppConfig appConfig : appConfigList) {
			omResponsibilityList.add(find(OmResponsibility.class, appConfig.getConfigValue()));
		}
		return omResponsibilityList;
	}
}