package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface OmUserDao extends BaseDao {

	public OmUser findUserwithUserName(String userName);

	public OmUser findByPrimaryKey(String id);

	public List<OmPosition> getPositionsOfUserByUserId(String id, int offset, int limit);

	public OmUser findByPrimaryKeyWithEagerLovs(String id);
	
	public OmUser findByUserIdEagerly(String userId);

}
