package uk.co.kayratech.m2m.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import uk.co.kayratech.m2m.platform.model.constants.EntityConstraints;

@Entity
@Table(name = "M2M_USER")
public class User extends BaseEntity {
	
	private static final long serialVersionUID = -5932873717409897470L;

	private String username;

	@Column(name = "USER_NAME", nullable = false)
	@Size(max = 50, message = EntityConstraints.USER_NAME_TOO_LONG_MSG_KEY)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
