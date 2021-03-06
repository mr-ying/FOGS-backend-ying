package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "UKey_Customer")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
@Audited
public class Customer extends EntityIdKeyBase {

    private static final long serialVersionUID = 4734287438084721089L;
    @NotNull
    @NotBlank
    @Size(max=10)
    private String code;
    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;
    @NotNull
    @NotBlank
    @Size(max=200)
    private String address;
    @Size(max=50)
    private String brand;
}
