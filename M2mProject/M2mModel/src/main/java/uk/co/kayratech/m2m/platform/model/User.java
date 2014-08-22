package uk.co.kayratech.m2m.platform.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
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
import uk.co.kayratech.m2m.platform.model.security.Role;

@Audited
@Entity
@Table(name = "M2M_USER")
// Example setup for two way encryption
@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "hibernateStringEncryptor") })
@NamedEntityGraph(name = "UserWithRoles", attributeNodes = @NamedAttributeNode("roles"))
public class User extends BaseEntity {

	private static final long serialVersionUID = -5932873717409897470L;

	private String username;
	// Example for two way encrypted data
	private String twoWayEncryptedData;
	// One way encrypted data
	private String password;
	private UserType userType;
	private Set<Role> roles = new HashSet<Role>();

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

	@Column(name = "ENCRYPTED_DATA", nullable = false)
	@Size(max = 50, message = "encdata.too.long")
	@Type(type = "encryptedString")
	public String getTwoWayEncryptedData() {
		return twoWayEncryptedData;
	}

	public void setTwoWayEncryptedData(String twoWayEncryptedData) {
		this.twoWayEncryptedData = twoWayEncryptedData;
	}

	@Column(name = "PASSWORD", nullable = false)
	@Size(max = 50, message = "password.too.long")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "USER_TYPE", nullable = false)
	@NotNull(message = "user.type.not.null")
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.DETACH })
	// @formatter:off
	@JoinTable(name = "M2M_USER_ROLE", 
		joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	// @formatter:on
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
