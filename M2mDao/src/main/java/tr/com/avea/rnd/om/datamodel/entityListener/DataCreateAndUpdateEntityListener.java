package tr.com.avea.rnd.om.datamodel.entityListener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import tr.com.avea.rnd.om.datamodel.model.BaseEntity;

public class DataCreateAndUpdateEntityListener {

	@NotAspectLogged
	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setLastUpdate(new Date());
		// TODO: Use spring security to get principal here and get the username from that
		entity.setLastUpdateBy(InheritableThreadLocalContext.instance.get().getUsername());
	}
	
	@NotAspectLogged
	@PrePersist
	public void prePersist(BaseEntity entity) {
		entity.setLastUpdate(new Date());
		entity.setCreated(entity.getLastUpdate());
		entity.setLastUpdateBy(InheritableThreadLocalContext.instance.get().getUsername());
		entity.setCreatedBy(entity.getLastUpdateBy());
	}

}
