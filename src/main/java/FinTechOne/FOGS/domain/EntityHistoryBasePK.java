package FinTechOne.FOGS.domain;


import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class EntityHistoryBasePK implements Serializable {
    private long id;
    private Date lastModifiedDate;
}
