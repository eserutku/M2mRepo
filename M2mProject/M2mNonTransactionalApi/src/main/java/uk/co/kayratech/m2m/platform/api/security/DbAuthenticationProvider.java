package uk.co.kayratech.m2m.platform.api.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import uk.co.kayratech.m2m.platform.model.security.Permission;
import uk.co.kayratech.m2m.platform.txlogic.PermissionTxLogic;

@Component
public class DbAuthenticationProvider implements AuthenticationProvider {

	private static Logger logger = LoggerFactory.getLogger(DbAuthenticationProvider.class);

	@Value("${db.authentication}")
	private String isDbAuthenticationEnabled;

	@Autowired
	private PermissionTxLogic permissionTxLogic;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.debug("DB authentication enbled flag: " + isDbAuthenticationEnabled);
		//		if (isDbAuthenticationEnabled == false) return null;

		List<Permission> permissions = permissionTxLogic.findAllPermissionsFromUsername(
				auth.getName(), auth.getCredentials().toString());
		if (permissions.size() == 0)
			return null;

		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}
}
