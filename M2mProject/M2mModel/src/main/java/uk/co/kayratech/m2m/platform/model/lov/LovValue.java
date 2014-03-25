package uk.co.kayratech.m2m.platform.model.lov;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import uk.co.kayratech.m2m.platform.common.context.BaseContext;
import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.model.BaseEntity;
import uk.co.kayratech.m2m.platform.model.exceptions.DisplayValueForLangNotFoundException;
import uk.co.kayratech.m2m.platform.model.lov.enums.Language;

@Entity
@Table(name = "M2M_LOV_VALUE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "LOV_DISC", discriminatorType = DiscriminatorType.STRING)
@Access(value = AccessType.PROPERTY)
public abstract class LovValue extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4252715971037488089L;

	private String lic; // Language Independent Code
	private String displayValue;
	private Language langOfCurrentDisplayValue;
	private LovValue parentLic;
	private Set<LovValue> childLics;
	private LovType lovType;
	private LovStatus status;
	private Boolean frozen;
	private Set<LovDisplayValue> displayValues = new HashSet<LovDisplayValue>();

	public LovValue() {
	}
	
	@Override
	protected StringBuffer buildStringRepresentation() {
		StringBuffer sb = new StringBuffer();
		sb.append("Lic: ");
		sb.append(lic);
		sb.append(" Display Value: ");
		sb.append(displayValue);
		return sb;
	}
	
	@Column(name = "LIC", unique = true, nullable = false, length = 40)
	public String getLic() {
		return lic;
	}

	public void setLic(String lic) {
		this.lic = lic;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PARENT_LIC_TECH_KEY", nullable = true)
	public LovValue getParentLic() {
		return parentLic;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentLic")
	public Set<LovValue> getChildLics() {
		return childLics;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOV_TYPE_TECH_KEY")
	public LovType getLovType() {
		return lovType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public LovStatus getStatus() {
		return status;
	}
	
	@Column(name = "FROZEN")
	@Type(type = "true_false")
	public Boolean getFrozen() {
		return frozen;
	}

	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	public void setStatus(LovStatus status) {
		this.status = status;
	}

	public void setLovType(LovType lovType) {
		this.lovType = lovType;
	}

	public void setParentLic(LovValue parentLic) {
		this.parentLic = parentLic;
	}

	public void setChildLics(Set<LovValue> childLics) {
		this.childLics = childLics;
	}

	public void populateClone(LovValue clonedObject) {
		clonedObject.setLovType(getLovType());
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if ((o instanceof LovValue) == false) {
			return false;
		}
		LovValue castedObj = (LovValue) o;
		if ((castedObj.getLic() == null) || (this.getLic() == null)) {
			return false;
		}
		return this.getLic().equals(castedObj.getLic());
	}

	public void setDisplayValues(Set<LovDisplayValue> displayValues) {
		this.displayValues = displayValues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lovValue")
	public Set<LovDisplayValue> getDisplayValues() {
		return displayValues;
	}

	@Transient
	public String findDisplayValueFromLanguage(Language currentLanguage) {
		for (LovDisplayValue displayValue : getDisplayValues()) {
			if (displayValue.getLanguage().name().equals(currentLanguage.name())) {
				return displayValue.getDisplayValue();
			}
		}
		throw new DisplayValueForLangNotFoundException(currentLanguage, getLovType(), this);
	}
	
	private boolean languageHasChanged() {
		Language langInContext = Language
				.getLanguageFromLocale(((BaseContext) InheritableThreadLocalContext.instance
						.get()).getLocale());
		return langInContext.equals(getLangOfCurrentDisplayValue());
	}

	@Transient
	public String getDisplayValue() {
		Language currentLanguage = null;
		if (getLangOfCurrentDisplayValue() != null) {
			currentLanguage = getLangOfCurrentDisplayValue();
		}
		else {
			currentLanguage = Language
					.getLanguageFromLocale(((BaseContext) InheritableThreadLocalContext.instance
							.get()).getLocale());
		}
		
		if ( (displayValue == null) || languageHasChanged() ) {
			setDisplayValue(findDisplayValueFromLanguage(currentLanguage));
			setLangOfCurrentDisplayValue(currentLanguage);
		}
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	@Transient
	public Language getLangOfCurrentDisplayValue() {
		return langOfCurrentDisplayValue;
	}

	public void setLangOfCurrentDisplayValue(Language langOfCurrentDisplayValue) {
		this.langOfCurrentDisplayValue = langOfCurrentDisplayValue;
	}

}
