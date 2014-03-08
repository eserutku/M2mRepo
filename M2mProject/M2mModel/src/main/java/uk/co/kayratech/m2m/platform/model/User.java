package uk.co.kayratech.m2m.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;

import uk.co.kayratech.m2m.platform.model.constants.EntityConstraints;

@Audited
@Entity
@Table(name = "M2M_USER")
public class User extends BaseEntity {
	
	private static final long serialVersionUID = -5932873717409897470L;

	private String username;
	
	@Override
	protected StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append("Username: ");
		sb.append(username);
		return sb;
	}

	@Column(name = "USERNAME", nullable = false)
	@Size(max = 50, message = EntityConstraints.USERNAME_TOO_LONG_MSG_KEY)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
