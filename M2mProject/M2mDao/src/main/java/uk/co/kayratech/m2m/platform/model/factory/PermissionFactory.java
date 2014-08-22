package uk.co.kayratech.m2m.platform.model.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.model.security.Permission;

@Component
public class PermissionFactory extends BaseFactory<Permission> {

	@Override
	public void populateObject(Permission objectToPopulate) {
		String uniqueName = UUID.randomUUID().toString();
		objectToPopulate.setName(uniqueName);
		objectToPopulate.setDescription("Permission " + uniqueName);
	}

}
