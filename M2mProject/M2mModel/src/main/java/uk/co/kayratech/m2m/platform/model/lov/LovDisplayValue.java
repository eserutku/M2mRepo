package uk.co.kayratech.m2m.platform.model.lov;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uk.co.kayratech.m2m.platform.model.BaseEntity;

@Entity
@Table(name = "M2M_LOV_DISPLAY_VALUE")
@Access(value = AccessType.PROPERTY)
public class LovDisplayValue extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4252715971037488089L;

	private LovValue lovValue;
	private String displayValue;
	private Language language;
	
	public LovDisplayValue() {
	}
	
	@Column(name = "DISPLAY_VALUE", nullable = false, length = 40)
	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "LANGUAGE", nullable = false)
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setLovValue(LovValue lovValue) {
		this.lovValue = lovValue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_ROW_ID")
	public LovValue getLovValue() {
		return lovValue;
	}
}
