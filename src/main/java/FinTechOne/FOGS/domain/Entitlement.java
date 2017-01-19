package FinTechOne.FOGS.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "userId", name = "UKey_Entitlement")})
@EntityListeners({AuditingEntityListener.class})
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper=false)
public class Entitlement extends EntityIdKeyBase {

    private static final long serialVersionUID = -6920051131047352660L;
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @NotNull
    @NotBlank
    @Size(max=10)
    private String userId;
    @NotNull
    @NotBlank
    @Size(max=20)
    private String name;
    @NotNull
    @NotBlank
    @Size(max=50)
    private String jobTitle;
    @NotNull
    @NotBlank
    @Size(max=72)
    @JsonIgnore
    @QueryType(PropertyType.NONE)
    private String password;
    @NotNull
    @NotBlank
    @Size(max=50)
    private String role;
    @NotNull
    @NotBlank
    @Email
    @Size(max=50)
    private String email;
    private boolean changePassword;
//    @Temporal(TemporalType.DATE)
//    private Date accountExpiryDate;
//    @Temporal(TemporalType.DATE)
//    private Date passwordExpiryDate;
//    private boolean accountLocked;
//    private boolean disabled;
    @Transient
    @JsonIgnore
    private String plainPassword;

    public void setPassword(String password) {
        this.plainPassword = password;
        this.password = PASSWORD_ENCODER.encode(password);
    }

//    @PrePersist
//    private void generatePassword() {
//        if (userId == "admin" || userId == "manager") {
//            setPassword(userId);
//        } else {
//            setPassword("password");
//        }
//        System.out.println("password is generated!!!");
//    }
//    @PostPersist
//    private void mailPassword() {
//
//    }
}
