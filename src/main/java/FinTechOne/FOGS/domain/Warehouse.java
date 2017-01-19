package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "UKey_Warehouse")})
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode(callSuper=false)
public class Warehouse extends EntityIdKeyBase {

    private static final long serialVersionUID = 3187046817902368510L;
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
}
