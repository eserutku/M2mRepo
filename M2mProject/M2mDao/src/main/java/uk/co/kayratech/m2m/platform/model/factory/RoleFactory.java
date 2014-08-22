package uk.co.kayratech.m2m.platform.model.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.model.security.Role;

@Component
public class RoleFactory extends BaseFactory<Role> {

	@Override
	public void populateObject(Role objectToPopulate) {
		String uniqueStr = UUID.randomUUID().toString();
		objectToPopulate.setDescription("Role of " + uniqueStr);
		objectToPopulate.setName("ROLE_" + uniqueStr.toUpperCase());
	}
}
