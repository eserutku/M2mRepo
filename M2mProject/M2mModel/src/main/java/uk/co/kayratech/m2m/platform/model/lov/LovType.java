package uk.co.kayratech.m2m.platform.model.lov;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Entity
@Table(name = "M2M_LOV_TYPE")
@Access(value = AccessType.PROPERTY)
public class LovType extends BaseEntity {

	private static final long serialVersionUID = 5543857337096989007L;

	private String lovType;
	private String description;
	private Boolean frozen;
	private LovType parentLovType;
	private Set<LovType> children;
	private Set<LovValue> values;

	@Override
	protected StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append("LovType: ");
		sb.append(lovType);
		
		return sb;
	}
	
	@Column(name = "LOV_NAME", unique = false, nullable = false, length = 30)
	public String getLovType() {
		return lovType;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, length = 100)
	public String getDescription() {
		return description;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PARENT_LOV_TECH_KEY")
	public LovType getParentLovType() {
		return parentLovType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentLovType")
	public Set<LovType> getChildren() {
		return children;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lovType")
	public Set<LovValue> getValues() {
		return values;
	}

	@Column(name = "FROZEN")
	@Type(type = "true_false")
	public Boolean getFrozen() {
		return frozen;
	}

	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	public void setValues(Set<LovValue> values) {
		this.values = values;
	}

	public void setParentLovType(LovType parentLovType) {
		this.parentLovType = parentLovType;
	}

	public void setChildren(Set<LovType> children) {
		this.children = children;
	}

	public void setLovType(String lovType) {
		this.lovType = lovType;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
