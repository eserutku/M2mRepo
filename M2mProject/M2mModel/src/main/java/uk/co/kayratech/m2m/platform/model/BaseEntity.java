package uk.co.kayratech.m2m.platform.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import javax.validation.metadata.ConstraintDescriptor;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import uk.co.kayratech.m2m.platform.common.context.InheritableThreadLocalContext;
import uk.co.kayratech.m2m.platform.common.i18n.MessageProvider;
import uk.co.kayratech.m2m.platform.model.constants.EntityConstraints;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String VALIDATION_MAX_SIZE_KEYWORD = "max";

	private String technicalId;
	private DateTime created;
	private String createdBy;
	private DateTime lastUpdate;
	private String lastUpdateBy;
	private int modificationNo;
	private String integrationId;

	@Id
	@Column(name = "TECHNICAL_KEY", nullable = false, unique = true, length = 36)
	@GenericGenerator(name = "M2M_TECH_KEY_UUID_SEQ", strategy = "uuid")
	@GeneratedValue(generator = "M2M_TECH_KEY_UUID_SEQ")
	public String getTechnicalId() {
		return this.technicalId;
	}

	public void setTechnicalId(String technicalId) {
		this.technicalId = technicalId;
	}

	@Column(name = "CREATED", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getCreated() {
		return this.created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 15)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "LAST_UPDATE", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	public DateTime getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(DateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name = "LAST_UPDATE_BY", nullable = false, length = 15)
	public String getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@Version
	@Column(name = "MODIFICATION_NO", nullable = false, precision = 9, scale = 0)
	public int getModificationNo() {
		return this.modificationNo;
	}

	public void setModificationNo(int modificationNo) {
		this.modificationNo = modificationNo;
	}

	@Column(name = "INTEGRATION_ID", nullable = false)
	@Size(max = EntityConstraints.INTEGRATION_ID_MAX_SIZE, message = EntityConstraints.INTEGRATION_ID_TOO_LONG_MSG_KEY)
	public String getIntegrationId() {
		return integrationId;
	}

	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}

	public static synchronized <T extends BaseEntity> List<String> validate(T unvalidatedObj) {
		List<String> validatitonMessages = new ArrayList<String>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		List<ConstraintViolation<T>> violations = new ArrayList<ConstraintViolation<T>>(
				validator.validate(unvalidatedObj));
		for (ConstraintViolation<T> constraintViolation : violations) {
			ConstraintDescriptor<?> constraintDesc = constraintViolation.getConstraintDescriptor();
			if (constraintDesc.getAnnotation() instanceof javax.validation.constraints.Size) {
				Object maxAllowedVal = constraintDesc.getAttributes().get(
						VALIDATION_MAX_SIZE_KEYWORD);
				Object valueGiven = constraintViolation.getInvalidValue();
				if (valueGiven instanceof String) {
					validatitonMessages.add(MessageProvider.getMessage(
							constraintViolation.getMessage(), new Object[] { maxAllowedVal,
									((String) valueGiven).length() }));
				}
				else {
					validatitonMessages.add(MessageProvider.getMessage(
							constraintViolation.getMessage(), new Object[] { maxAllowedVal,
									"invalid" }));
				}
			}
			else {
				validatitonMessages
						.add(MessageProvider.getMessage(constraintViolation.getMessage()));
			}
		}
		return validatitonMessages;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || (obj instanceof BaseEntity) == false) {
			return false;
		}

		BaseEntity beObj = (BaseEntity) obj;
		EqualsBuilder builder = new EqualsBuilder();

		return builder.append(this.getTechnicalId(), beObj.getTechnicalId())
				.append(this.getIntegrationId(), beObj.getIntegrationId()).isEquals();
	}

	@Override
	public int hashCode() {
		if (this.getTechnicalId() == null) {
			return 0;
		}
		else {
			return getTechnicalId().hashCode();
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" techId: ");
		sb.append(getTechnicalId());
		sb.append(" ");
		sb.append(buildStringRepresentation());
		return new String(sb);
	}

	protected abstract StringBuffer buildStringRepresentation();

	@PrePersist
	public void prePersist() {
		setCreated(new DateTime());
		setLastUpdate(new DateTime());
		// TODO: Use Spring security to get the principal here. Not thread local
		String user = InheritableThreadLocalContext.instance.get().getUsername();
		setCreatedBy(user);
		setLastUpdateBy(user);
	}

	@PreUpdate
	public void preUpdate() {
		setLastUpdate(new DateTime());
		// TODO: Use Spring security to get the principal here. Not thread local
		String user = InheritableThreadLocalContext.instance.get().getUsername();
		setLastUpdateBy(user);
	}
}