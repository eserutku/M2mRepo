package uk.co.kayratech.m2m.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import uk.co.kayratech.m2m.platform.model.constants.EntityConstraints;
import uk.co.kayratech.m2m.platform.model.lov.UserType;

@Audited
@Entity
@Table(name = "M2M_USER")
public class User extends BaseEntity {
	
	private static final long serialVersionUID = -5932873717409897470L;

	private String username;
	private UserType userType;
	
	public User() {
	}
	
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
	
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_TYPE", nullable = false)
	@NotNull(message = EntityConstraints.USER_TYPE_NOT_NULL_MSG_KEY)
	public UserType getUserType() {
		return userType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
