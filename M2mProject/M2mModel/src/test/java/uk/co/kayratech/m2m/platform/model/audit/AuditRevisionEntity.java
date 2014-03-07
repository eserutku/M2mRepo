package uk.co.kayratech.m2m.platform.model.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Table(name="M2M_REVINFO")
@SequenceGenerator(name="AUDIT_SEQ", sequenceName="AUDIT_ID_SEQ", allocationSize=1)
@RevisionEntity(AuditListenerEntity.class)
public class AuditRevisionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDIT_SEQ")
	@RevisionNumber
	@Column(name="REV")
	private int revId;
	
	@RevisionTimestamp
	@Column(name="REVTSTMP")
	private long revisionTs;
	
	@Column(name="USERNAME")
	private String username;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getRevisionTs() {
		return revisionTs;
	}

	public void setRevisionTs(long revisionTs) {
		this.revisionTs = revisionTs;
	}

	public int getRevId() {
		return revId;
	}

	public void setRevId(int revId) {
		this.revId = revId;
	}
}
