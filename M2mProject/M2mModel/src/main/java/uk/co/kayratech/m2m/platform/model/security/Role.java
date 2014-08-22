package uk.co.kayratech.m2m.platform.model.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import uk.co.kayratech.m2m.platform.model.BaseEntity;
import uk.co.kayratech.m2m.platform.model.User;

@Audited
@Entity
@Table(name = "M2M_ROLE")
@NamedEntityGraph(name = "RoleWithPermissions", attributeNodes = @NamedAttributeNode("permissions"))
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private Set<Permission> permissions = new HashSet<Permission>();
	private Set<User> grantedToUsers = new HashSet<User>();

	@NotNull(message = "role.name.not.null")
	@Size(max = 50, message = "role.name.too.long")
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Size(max = 200, message = "role.desc.too.long")
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotAudited
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	// @formatter:off
	@JoinTable(name = "M2M_ROLE_PERMISSIONS", 
		joinColumns = { @JoinColumn(name = "PERMISSION_ID", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
	// @formatter:on
	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	// @formatter:off
	@JoinTable(name = "M2M_USER_ROLE", 
		joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
	// @formatter:on
	public Set<User> getGrantedToUsers() {
		return grantedToUsers;
	}

	public void setGrantedToUsers(Set<User> grantedToUsers) {
		this.grantedToUsers = grantedToUsers;
	}

	@Override
	protected StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName());

		return sb;
	}

	@Override
	public int hashCode() {
		if (getName() == null)
			return super.hashCode();
		return getName().hashCode();
	}
}
