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
import java.io.Serializable;

/**
 * Created by User on 17/1/2017.
 */
@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "UKey_Factory")})
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode(callSuper=false)
public class Factory extends EntityIdKeyBase{

    private static final long serialVersionUID = 3024457286881703745L;

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
    private boolean outsourced;
    @NotNull
    @NotBlank
    @Email
    @Size(max=40)
    private String email;
    @NotNull
    @NotBlank
    @Size(max=40)
    @PhoneNumber
    private String contactNum;
}
