package uk.co.kayratech.m2m.platform.model.audit;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Table(name="M2M_REVINFO")
@SequenceGenerator(name="AUDIT_SEQ", sequenceName="M2M_AUDIT_ID_SEQ", allocationSize=1)
@RevisionEntity(AuditListenerEntity.class)
public class AuditRevisionEntity {

	private int revId;
	private Date revisionTs;
	private String username;
    private Set<String> modifiedEntityNames;

    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDIT_SEQ")
	@RevisionNumber
	@Column(name="REV_NO")
	public int getRevId() {
		return revId;
	}

	public void setRevId(int revId) {
		this.revId = revId;
	}
	
	@RevisionTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REV_TIMESTAMP")
	public Date getRevisionTs() {
		return revisionTs;
	}

	public void setRevisionTs(Date revisionTs) {
		this.revisionTs = revisionTs;
	}
	
    @ElementCollection
    @JoinTable(name = "M2M_REVCHANGES", joinColumns = { @JoinColumn(name = "REV_NO") } )
    @Column(name = "ENTITY_NAME")
    @ModifiedEntityNames
	public Set<String> getModifiedEntityNames() {
		return modifiedEntityNames;
	}

	public void setModifiedEntityNames(Set<String> modifiedEntityNames) {
		this.modifiedEntityNames = modifiedEntityNames;
	}

	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
