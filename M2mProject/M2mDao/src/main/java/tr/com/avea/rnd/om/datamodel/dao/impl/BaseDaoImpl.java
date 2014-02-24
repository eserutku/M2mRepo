package tr.com.avea.rnd.om.datamodel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReaderFactory;

import uk.co.kayratech.m2m.platform.dao.BaseDao;

public abstract class BaseDaoImpl implements BaseDao {
	
	private static final int MAX_RESULTS = 1000;
	
	@PersistenceContext(unitName = "M2m")
	protected EntityManager em;
	
	@NotAspectLogged
	public <T> T findAudit(Class<T> classT, Object primaryKey, long revisionId) {
		return AuditReaderFactory.get(em).find(classT, primaryKey, revisionId);
	}
	
	protected int getMaxAllowedResults() {
		return MAX_RESULTS;
	}
	
	@NotAspectLogged
	protected List<String> getCommonSysAttributes() {
		List<String> commonAttr = new ArrayList<String>();
		commonAttr.add("created");
		commonAttr.add("createdBy");
		commonAttr.add("lastUpdate");
		commonAttr.add("lastUpdateBy");
		return commonAttr;
	}
	
	@NotAspectLogged
	protected void setIgnoreOnSysColumns(Example example, List<String> attributes) {
		for(int i=0; i<attributes.size(); i++) {
			example.excludeProperty(attributes.get(i));
		}
	}
}