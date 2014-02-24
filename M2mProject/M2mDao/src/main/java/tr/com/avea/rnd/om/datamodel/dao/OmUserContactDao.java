package tr.com.avea.rnd.om.datamodel.dao;


public interface OmUserContactDao extends BaseDao {
	
	public OmUserContact findByTcId(String tcId);

}
