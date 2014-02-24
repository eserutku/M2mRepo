package tr.com.avea.rnd.om.datamodel.dao;

import java.util.List;

public interface LovDao extends BaseDao {

	public <T extends BaseLov> T getLovFromLic(T lov) throws TooManyRowsRetrievedFromQueryException;

	public <T extends BaseLov> List<T> findByExample(T example, boolean eagerFetch);

	public List<String> findDistinctBaseLovDisc();

	public List<OmLovName> getAllLovNames();

	public <T extends BaseLov> List<T> findByLovName(OmLovName omLovName);

	public <T extends BaseLov> List<T> getLovsByFrozenFlag(boolean isFrozen);
	
	public List<String> findLicByBaseLovDisc(String lovName);

	public <T extends BaseLov> T getAnyLov(T lov) throws LovTypeDoesNotExistInSystemException;

}
