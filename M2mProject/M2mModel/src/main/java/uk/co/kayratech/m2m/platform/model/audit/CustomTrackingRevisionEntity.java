package uk.co.kayratech.m2m.platform.model.audit;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

//@Entity
//@RevisionEntity(CustomEntityTrackingRevisionListener.class)
public class CustomTrackingRevisionEntity {
//    @Id
//    @GeneratedValue
//    @RevisionNumber
//    private int customId;
//
//    @RevisionTimestamp
//    private long customTimestamp;
//
//    @OneToMany(mappedBy="revision", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
//    private Set<ModifiedEntityTypeEntity> modifiedEntityTypes =
//                                              new HashSet<ModifiedEntityTypeEntity>();
//    
//    public void addModifiedEntityType(String entityClassName) {
//        modifiedEntityTypes.add(new ModifiedEntityTypeEntity(this, entityClassName));
//    }
//
//	public int getCustomId() {
//		return customId;
//	}
//
//	public void setCustomId(int customId) {
//		this.customId = customId;
//	}
//
//	public long getCustomTimestamp() {
//		return customTimestamp;
//	}
//
//	public void setCustomTimestamp(long customTimestamp) {
//		this.customTimestamp = customTimestamp;
//	}
//
//	public Set<ModifiedEntityTypeEntity> getModifiedEntityTypes() {
//		return modifiedEntityTypes;
//	}
//
//	public void setModifiedEntityTypes(Set<ModifiedEntityTypeEntity> modifiedEntityTypes) {
//		this.modifiedEntityTypes = modifiedEntityTypes;
//	}
}
