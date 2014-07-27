package uk.co.kayratech.m2m.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.jasypt.hibernate4.type.EncryptedStringType;

import uk.co.kayratech.m2m.platform.model.lov.UserType;

@Audited
@Entity
@Table(name = "M2M_USER")
// Example setup for two way encryption
@TypeDef(
        name="encryptedString", 
        typeClass=EncryptedStringType.class, 
        parameters={@Parameter(name="encryptorRegisteredName",
                               value="hibernateStringEncryptor")}
)
public class User extends BaseEntity {
	
	private static final long serialVersionUID = -5932873717409897470L;

	private String username;
	// Example for two way encrypted data
	private String twoWayEncryptedData;
	private UserType userType;
	
	public User() {
	}
	
	@Override
	public StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append("Username: ");
		sb.append(username);
		return sb;
	}

	@Column(name = "USERNAME", nullable = false)
	@Size(max = 50, message = "username.too.long")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false)
	@Size(max = 50, message = "password.too.long")
	@Type(type="encryptedString")
	public String getTwoWayEncryptedData() {
		return twoWayEncryptedData;
	}

	public void setTwoWayEncryptedData(String twoWayEncryptedData) {
		this.twoWayEncryptedData = twoWayEncryptedData;
	}

	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_TYPE", nullable = false)
	@NotNull(message = "user.type.not.null")
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
