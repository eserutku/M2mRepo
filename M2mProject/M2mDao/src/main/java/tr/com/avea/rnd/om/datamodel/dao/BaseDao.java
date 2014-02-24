package tr.com.avea.rnd.om.datamodel.dao;

public interface BaseDao {
	
	public <T> void save(T persistentObject);
	
	public <T> Object update(T persistentObject);	

	public <T> void delete(T persistentObject);

	public <T> T find(Class<T> classT, Object primaryKey);
	
	public <T> T findAudit(Class<T> classT, Object primaryKey, long revisionId);

}