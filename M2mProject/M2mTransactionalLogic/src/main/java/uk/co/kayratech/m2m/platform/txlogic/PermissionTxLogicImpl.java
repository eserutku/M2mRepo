package uk.co.kayratech.m2m.platform.txlogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.dao.PermissionDao;
import uk.co.kayratech.m2m.platform.dao.UserDao;
import uk.co.kayratech.m2m.platform.model.User;
import uk.co.kayratech.m2m.platform.model.security.Permission;

@Component
public class PermissionTxLogicImpl extends BaseTxLogicImpl<Permission> implements PermissionTxLogic {

	@Autowired
	private PermissionDao dao;
	@Autowired
	private UserDao userDao;

	@Override
	public List<Permission> findAllPermissionsFromUsername(String username, String pass) {
		User user = userDao.findByUsername(username);
		if (user == null || user.getRoles().size() == 0)
			return new ArrayList<Permission>();

		return dao.findByRoles(user.getRoles());
	}

	@Override
	protected JpaRepository<Permission, String> getDao() {
		return dao;
	}

}
