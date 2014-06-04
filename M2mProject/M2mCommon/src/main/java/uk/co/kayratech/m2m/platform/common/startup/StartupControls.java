package uk.co.kayratech.m2m.platform.common.startup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class StartupControls {
	public static final String MASTER_KEY_ENV_VAR = "MASTER_KEY";
	public static final String ENVIRONMENT_ENV_VAR = "APP_ENVIRONMENT";

	public static final String CHK_ENVIRONMENT_FLAG = "startup.check.environment";
	public static final String CHK_MASTER_KEY_FLAG = "startup.check.master.key";
	public static final String COMPILE_ENV_PARAM = "compile.environment";
	
	private static final String PROERTY_FILE_PATH = "classpath:/conf/m2mCommonApplication.properties";

	static {
		Properties prop = getPropertiesObject();
		
		String errMsg = "";
		errMsg = checkBasicPropertiesExist(prop, errMsg);
		if (StringUtils.isEmpty(errMsg) == false)
			throw new RuntimeException(errMsg);

		String startupChecksErrorMessages = "";
		startupChecksErrorMessages = checkPackageIsCompiledForThisEnvironment(prop);
		startupChecksErrorMessages = checkMasterKeyIsGivenAsAnEnvironmentVariable(prop);
		
		if (StringUtils.isEmpty(startupChecksErrorMessages) == false) {
			// One or more of the checks above failed
			startupChecksErrorMessages += " - You must fix these errors before application can start";
			throw new RuntimeException(startupChecksErrorMessages);
		}
	}

	private static String checkBasicPropertiesExist(Properties prop, String errMsg) {
		if (prop.get(CHK_ENVIRONMENT_FLAG) == null) {
			errMsg += " The following property is not found in property file " + PROERTY_FILE_PATH
					+ " - " + CHK_ENVIRONMENT_FLAG + " (should be set true/false)";
		}
		if (prop.get(CHK_MASTER_KEY_FLAG) == null) {
			errMsg += " The following property is not found in property file " + PROERTY_FILE_PATH
					+ " - " + CHK_MASTER_KEY_FLAG + " (should be set true/false)";
		}
		return errMsg;
	}

	private static Properties getPropertiesObject() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(PROERTY_FILE_PATH);
			prop.load(input);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return prop;
	}
	
	private static String checkPackageIsCompiledForThisEnvironment(Properties prop) {
		String errorMessages = "";
		boolean chkEnvironment = (Boolean) prop.get(CHK_ENVIRONMENT_FLAG);
		
		if (chkEnvironment) {
			String environmentPackageCompiledFor = (String) prop.getProperty(COMPILE_ENV_PARAM);
			if (StringUtils.isEmpty(environmentPackageCompiledFor)) {
				errorMessages += " " + COMPILE_ENV_PARAM + " is not found in property file "
						+ PROERTY_FILE_PATH + " but " + CHK_ENVIRONMENT_FLAG
						+ " is true. Either disable " + CHK_ENVIRONMENT_FLAG + " or set "
						+ COMPILE_ENV_PARAM + " in configuration file";
			}
			else {
				String thisEnvironment = System.getenv(ENVIRONMENT_ENV_VAR);
				if (StringUtils.isEmpty(thisEnvironment)) {
					errorMessages += "Environment Variable " + ENVIRONMENT_ENV_VAR
							+ " is not found. This package is built for " + COMPILE_ENV_PARAM
							+ " set the environment variable to this or set "
							+ CHK_ENVIRONMENT_FLAG + " to false in " + PROERTY_FILE_PATH
							+ " to skip this check";
				}
				else {
					if (environmentPackageCompiledFor.compareToIgnoreCase(thisEnvironment) != 0) {
						errorMessages += "The compiled package is a "
								+ environmentPackageCompiledFor + " package. This environment is "
								+ thisEnvironment
								+ ". You must recompile a package for this environment or set the "
								+ ENVIRONMENT_ENV_VAR + " environment variable to "
								+ environmentPackageCompiledFor + " or " + CHK_ENVIRONMENT_FLAG
								+ " to false in " + PROERTY_FILE_PATH + " and skip this check (not advised!)";
					}
				}
			}
		}
		
		return errorMessages;
	}
	
	private static String checkMasterKeyIsGivenAsAnEnvironmentVariable(Properties prop) {
		String errorMessages = "";
		boolean chkMasterKey = (Boolean) prop.get(CHK_MASTER_KEY_FLAG);

		if (chkMasterKey) {
			String masterPass = System.getenv(MASTER_KEY_ENV_VAR);
			if (StringUtils.isEmpty(masterPass)) {
				errorMessages += "Environment Variable " + MASTER_KEY_ENV_VAR
						+ " is not found. You can enter the master key now to continue";
				throw new RuntimeException();
			}
		}
		
		return errorMessages;
	}
}
