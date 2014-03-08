package uk.co.kayratech.m2m.platform.model.audit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ModifiedEntityTypeEntity {
    public ModifiedEntityTypeEntity(AuditRevisionEntity revision,
			String entityClassName) {
    	this.revision = revision;
    	this.entityClassName = entityClassName;
	}

	@Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private AuditRevisionEntity revision;
    
    private String entityClassName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AuditRevisionEntity getRevision() {
		return revision;
	}

	public void setRevision(AuditRevisionEntity revision) {
		this.revision = revision;
	}

	public String getEntityClassName() {
		return entityClassName;
	}

	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}
}
