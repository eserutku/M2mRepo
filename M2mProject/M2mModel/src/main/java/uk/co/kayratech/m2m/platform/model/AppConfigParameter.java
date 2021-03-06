package uk.co.kayratech.m2m.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "M2M_APP_CONFIG")
public class AppConfigParameter extends BaseEntity {

	private static final long serialVersionUID = 8970263766658831173L;

	private String paramName;
	private String paramValue;
	private String description;
	
	public AppConfigParameter() {
	}
	
	@Override
	protected StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append("Param name: ");
		sb.append(paramName);
		sb.append(" Param value: ");
		sb.append(paramValue);
		return sb;
	}

	@Column(name = "PARAM_NAME", nullable = false)
	@Size(max = 100, message = "config.param.name.too.long")
	public String getParamName() {
		return paramName;
	}

	@Column(name = "PARAM_VALUE", nullable = false)
	@Size(max = 100, message = "config.param.value.too.long")
	public String getParamValue() {
		return paramValue;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	@Size(max = 100, message = "config.description.too.long")
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
