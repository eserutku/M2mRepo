package uk.co.kayratech.m2m.platform.model.support;

import uk.co.kayratech.m2m.platform.model.User;

public class UserSupport extends BaseSupport<User> {
	
	@Override
	public void populateObjectToBeSaved(User user) {
		BaseEntitySupport.populateBaseEntityToSave(user);
		user.setUsername("username");
	}
}
