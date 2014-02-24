package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import uk.co.kayratech.m2m.platform.dao.LovDao;

@Repository("lovDao")
public class LovDaoImpl extends BaseDaoImpl implements LovDao {

	@NotAspectLogged
	@Override
	public <T extends BaseLov> T getLovFromLic(T lovWithLic)
			throws TooManyRowsRetrievedFromQueryException {
		OmContext ctx = (OmContext) InheritableThreadLocalContext.instance
				.get();
		Language language = ctx.getLanguage();
		Criteria crit = getSession().createCriteria(lovWithLic.getClass());
		crit.add(Restrictions.eq("lic", lovWithLic.getLic()));
		crit.add(Restrictions.eq("language", language));
		crit.add(Restrictions.eq("status", LovStatus.IN_USE));

		@SuppressWarnings("unchecked")
		List<T> resultset = (List<T>) crit.list();
		if (resultset.size() > 1) {
			throw new TooManyRowsRetrievedFromQueryException("Query with "
					+ lovWithLic.getLic() + " failed", 1, resultset.size());
		} else if (resultset.size() == 0) {
			return null;
		} else {
			return resultset.get(0);
		}
	}

	@NotAspectLogged
	@Override
	public <T extends BaseLov> T getAnyLov(T lov)
			throws LovTypeDoesNotExistInSystemException {
		Criteria crit = getSession().createCriteria(lov.getClass());
		crit.add(Restrictions.eq("status", LovStatus.IN_USE));

		@SuppressWarnings("unchecked")
		List<T> resultset = (List<T>) crit.list();
		if (resultset.size() == 0) {
			throw new LovTypeDoesNotExistInSystemException("Lov not found", lov);
		} else {
			return resultset.get(0);
		}
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY, readOnly = true)
	public <T extends BaseLov> List<T> findByExample(T example,
			boolean eagerFetch) {
		Example exampleObj = setUpExampleAndEnableLike(example);
		Criteria crit = getSession().createCriteria(example.getClass());
		crit.add(exampleObj);

		if (eagerFetch) {
			crit.setFetchMode("parentLic", FetchMode.JOIN);
			crit.setFetchMode("childLics", FetchMode.JOIN);
			crit.setFetchMode("lovName", FetchMode.JOIN);
		}

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		@SuppressWarnings("unchecked")
		List<T> resultset = (List<T>) crit.list();

		return resultset;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDistinctBaseLovDisc() {
		Query query = em
				.createQuery("select distinct l.lovName from BaseLov b, OmLovName l where b.lovName=l.rowId");
		List<String> retval = query.getResultList();
		return retval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseLov> List<T> getLovsByFrozenFlag(boolean isFrozen) {
		Query query = em
				.createQuery("select b from BaseLov b, OmLovName l where b.lovName=l.rowId and l.frozen=:isFrozen order by l.lovName");
		query.setParameter("isFrozen", isFrozen);
		List<T> retval = query.getResultList();
		return retval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OmLovName> getAllLovNames() {
		Query query = em.createQuery("from OmLovName");
		List<OmLovName> retval = query.getResultList();
		return retval;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseLov> List<T> findByLovName(OmLovName omLovName) {
		Query query = em.createQuery("from BaseLov where lovName=:omLovName");
		query.setParameter("omLovName", omLovName);
		List<T> retval = query.getResultList();
		return retval;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findLicByBaseLovDisc(String lovName) {
		Query query = em
				.createQuery("select b.lic from BaseLov b, OmLovName l where b.lovName=l.rowId and l.lovName=:lovName");
		query.setParameter("lovName", lovName);
		List<String> retval = query.getResultList();
		return retval;
	}

}
