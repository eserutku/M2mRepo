package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface OmAccountDao extends BaseDao {
	
	public OmAccount findByPrimaryKey(String primaryKey) throws OmException;
	
	public List<OmAccount> findByExample(OmAccount omAccount) throws OmException;
}