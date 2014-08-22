package uk.co.kayratech.m2m.platform.model.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

// As permissions are used to control access to methods, each new permission will be added manually as a
// result of adding a new service method. Therefore, this entity is not audited
@Entity
@Table(name = "M2M_PERMISSION")
public class Permission extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private Set<Role> parentRoles;

	@NotNull(message = "perm.name.not.null")
	@Column(name = "NAME")
	@Size(max = 50, message = "perm.name.too.long")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	@Size(max = 50, message = "perm.desc.too.long")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	// @formatter:off
	@JoinTable(name = "M2M_ROLE_PERMISSIONS", 
		joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID", nullable = false, updatable = false) })
	// @formatter:on
	public Set<Role> getParentRoles() {
		return parentRoles;
	}

	public void setParentRoles(Set<Role> parentRoles) {
		this.parentRoles = parentRoles;
	}

	@Override
	protected StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName());

		return sb;
	}

}
