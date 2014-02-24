package uk.co.kayratech.m2m.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import uk.co.kayratech.m2m.platform.model.constants.EntityConstraints;

@Entity
@Table(name = "M2M_APP_CONFIG")
public class AppConfigParameter extends BaseEntity {

	private static final long serialVersionUID = 8970263766658831173L;

	private String paramName;
	private String paramValue;
	private String description;

	@Column(name = "PARAM_NAME", nullable = false)
	@Size(max = 100, message = EntityConstraints.PARAM_NAME_TOO_LONG_MSG_KEY)
	public String getParamName() {
		return paramName;
	}

	@Column(name = "PARAM_VALUE", nullable = false)
	@Size(max = 100, message = EntityConstraints.PARAM_VALUE_TOO_LONG_MSG_KEY)
	public String getParamValue() {
		return paramValue;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	@Size(max = 100, message = EntityConstraints.PARAM_VALUE_TOO_LONG_MSG_KEY)
	public String getDescription() {
		return description;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
