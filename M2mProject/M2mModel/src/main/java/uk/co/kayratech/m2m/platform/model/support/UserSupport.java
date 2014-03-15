package uk.co.kayratech.m2m.platform.model.support;

import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.model.User;

@Component
public class UserSupport extends BaseSupport<User> {
	
	@Override
	public void populateObjectToBeSaved(User user) {
		populateBaseEntityToSave(user);
		user.setUsername("username");
	}
}
