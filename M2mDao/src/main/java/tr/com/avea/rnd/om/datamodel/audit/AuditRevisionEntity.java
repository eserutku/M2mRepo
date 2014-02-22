package tr.com.avea.rnd.om.datamodel.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="REVINFO")
@SequenceGenerator(name="AUDIT_SEQ", sequenceName="AUDIT_ID_SEQ", allocationSize=1)
@RevisionEntity(AuditListenerEntity.class)
public class AuditRevisionEntity {

	private static final long serialVersionUID = 1834755194658197187L;

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
