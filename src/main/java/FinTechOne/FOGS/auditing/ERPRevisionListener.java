package FinTechOne.FOGS.auditing;


import FinTechOne.FOGS.domain.RevInfo;
import org.hibernate.envers.RevisionListener;

public class ERPRevisionListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {

        ERPSecurityAuditorAware auditorAware = new ERPSecurityAuditorAware();
        ((RevInfo)revisionEntity).setUser(auditorAware.getCurrentAuditor());

    }
}
