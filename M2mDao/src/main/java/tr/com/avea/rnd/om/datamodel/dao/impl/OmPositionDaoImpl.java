package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import tr.com.avea.rnd.om.datamodel.dao.OmPositionDao;

@Repository("omPositionDao")
public class OmPositionDaoImpl extends BaseDaoImpl implements OmPositionDao {

	private static final long serialVersionUID = 8159859833495862909L;

	private static final int MAX_ALLOWED_RESULT = 1000;

	protected int getMaxAllowedResults() {
		return MAX_ALLOWED_RESULT;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<OmPosition> findByExample(OmPosition omPosition) {
		return findByExample(omPosition, 0, getMaxAllowedResults());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<OmPosition> findByExample(OmPosition example, int offset, int limit) {
		limit = imposeMaxLimit(limit);

		Criteria crit = createPosCritWithSubObjects(example);
		addSubObjectCriteria(crit, example, FetchMode.DEFAULT);

		@SuppressWarnings("unchecked")
		List<OmPosition> positions = (List<OmPosition>) crit.setFirstResult(offset).setMaxResults(limit)
				.list();
		return positions;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<OmPosition> findByExampleAndFetchLovsEagerly(OmPosition example, int offset, int limit) {
		limit = imposeMaxLimit(limit);

		Criteria crit = createPosCritWithSubObjects(example);
		addSubObjectCriteria(crit, example, FetchMode.JOIN);

		@SuppressWarnings("unchecked")
		List<OmPosition> positions = (List<OmPosition>) crit.setFirstResult(offset).setMaxResults(limit)
				.list();
		return positions;
	}

	private Criteria createPosCritWithSubObjects(OmPosition example) {
		Example posExample = setUpExampleAndEnableLike(example);
		Criteria crit = getSession().createCriteria(OmPosition.class);
		crit.add(posExample);
		if (example.getId() != null) {
			crit.add(Restrictions.idEq(example.getId()));
		}
		if (example.getOmUsers().isEmpty() == false) {
			crit.createAlias("omUsers", "u");
			List<OmUser> users = new ArrayList<OmUser>();
			users.addAll(example.getOmUsers());
			crit.add(Restrictions.eq("u.rowId", users.get(0).getId()));
		}
		return crit;
	}

	private void addSubObjectCriteria(Criteria crit, OmPosition example, FetchMode fetchMode) {
		if (example == null) {
			return;
		}

		crit.setFetchMode("positionStatus", fetchMode);
		if (example.getPositionStatus() != null) {
			crit.createCriteria("positionStatus").add(setUpExampleAndEnableLike(example.getPositionStatus())).setFetchMode("positionStatus", fetchMode);
		}

		crit.setFetchMode("positionType", fetchMode);
		if (example.getPositionType() != null) {
			crit.createCriteria("positionType").add(setUpExampleAndEnableLike(example.getPositionType()));
		}

		crit.setFetchMode("dealerType", fetchMode);
		crit.setFetchMode("dealerType.parentLic", fetchMode);
		if (example.getDealerType() != null) {
			Criteria dtCrit = crit.createCriteria("dealerType").add(setUpExampleAndEnableLike(example.getDealerType()));
			if (example.getDealerType().getParentLic() != null) {
				dtCrit.createCriteria("parentLic").add(setUpExampleAndEnableLike(example.getDealerType().getParentLic()));
			}
		}
		
		crit.setFetchMode("salesRegion", fetchMode);
		if (example.getSalesRegion() != null) {
			crit.createCriteria("salesRegion").add(setUpExampleAndEnableLike(example.getSalesRegion()));
		}
		
		crit.setFetchMode("aveaOrganization", fetchMode);
		if (example.getAveaOrganization() != null) {
			crit.createCriteria("aveaOrganization").add(
					setUpExampleAndEnableLike(example.getAveaOrganization()));
		}
		
		crit.setFetchMode("superUser", fetchMode);
		if (example.getSuperUser() != null) {
			crit.createCriteria("superUser").add(setUpExampleAndEnableLike(example.getSuperUser()));
		}
	}

}
