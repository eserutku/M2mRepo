package uk.co.kayratech.m2m.platform.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "M2M_REVINFO")
public class Revinfo implements java.io.Serializable {

	private static final long serialVersionUID = -742877170580678196L;
	
	private long rev;
	private BigDecimal revtstmp;
	private String username;

	public Revinfo() {
	}

	public Revinfo(long rev, String username) {
		this.rev = rev;
		this.username = username;
	}

	public Revinfo(long rev, BigDecimal revtstmp, String username) {
		this.rev = rev;
		this.revtstmp = revtstmp;
		this.username = username;
	}

	@Id
	@Column(name = "REV", unique = true, nullable = false, precision = 10, scale = 0)
	public long getRev() {
		return this.rev;
	}

	public void setRev(long rev) {
		this.rev = rev;
	}

	@Column(name = "REVTSTMP", scale = 0)
	public BigDecimal getRevtstmp() {
		return this.revtstmp;
	}

	public void setRevtstmp(BigDecimal revtstmp) {
		this.revtstmp = revtstmp;
	}

	@Column(name = "USERNAME", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
