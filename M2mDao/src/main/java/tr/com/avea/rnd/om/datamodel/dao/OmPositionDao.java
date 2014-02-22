package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface OmPositionDao extends BaseDao {

	public List<OmPosition> findByExample(OmPosition pos);

	public List<OmPosition> findByExampleAndFetchLovsEagerly(OmPosition example, int offset, int limit);

	public List<OmPosition> findByExample(OmPosition example, int offset, int limit);

}
