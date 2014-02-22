package uk.co.kayratech.m2m.platform.model.lov;

import java.io.Serializable;

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
@Table(name = "OM_LOV_DISPLAY_VALUE")
public class OmBaseLdc extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -4252715971037488089L;

	private LovValue omBaseLov;
	private String ldc;
	private Language language;
	
	public OmBaseLdc() {
	}
	
	@Column(name = "DISPLAY_VALUE", nullable = false, length = 40)
	public String getLdc() {
		return ldc;
	}

	public void setLdc(String ldc) {
		this.ldc = ldc;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "LANGUAGE", nullable = false)
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setBaseLov(LovValue omBaseLov) {
		this.omBaseLov = omBaseLov;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIC_ROW_ID")
	public LovValue getBaseLov() {
		return omBaseLov;
	}
}
