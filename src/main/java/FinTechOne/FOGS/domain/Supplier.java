package FinTechOne.FOGS.domain;

import FinTechOne.FOGS.validator.hibernate.PhoneNumber;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "UKey_Supplier")})
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode(callSuper=false)
public class Supplier extends EntityIdKeyBase {

    private static final long serialVersionUID = -7413067342875176146L;

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
    @NotNull
    @NotBlank
    @Size(max=40)
    private String type;
    @Email
    @Size(max=40)
    private String email;
    @Size(max=40)
    @PhoneNumber
    private String contactNum;

}
