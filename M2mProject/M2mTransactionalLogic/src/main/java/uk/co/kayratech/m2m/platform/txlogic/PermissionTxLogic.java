package uk.co.kayratech.m2m.platform.txlogic;

import java.util.List;

import uk.co.kayratech.m2m.platform.model.security.Permission;

public interface PermissionTxLogic extends BaseTxLogic<Permission> {
	List<Permission> findAllPermissionsFromUsername(String username, String pass);
}
